package com.meslize.fredloveslluny.data.datasource.altbeacon;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;
import com.meslize.fredloveslluny.data.datasource.altbeacon.util.CleanRepeatedAltBeacon;
import com.meslize.fredloveslluny.data.mapper.LlunyEntityMapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

public class AltBeaconDataSourceImpl implements AltBeaconDataSource, BeaconConsumer, RangeNotifier {

  static final String LLUNY_LAYOUT = "m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24";
  final Context mContext;
  final BeaconManager mBeaconManager;
  private final Region mRegion = new Region("myRangingUniqueId", null, null, null);
  int mSeconds;
  Callback mCallback;
  LlunyEntityMapper mLlunyEntityMapper = new LlunyEntityMapper();
  List<Beacon> mBeacons = new ArrayList<>();
  CleanRepeatedAltBeacon mCleanRepeatedAltBeacon = new CleanRepeatedAltBeacon();

  public AltBeaconDataSourceImpl(Context context) {
    mContext = context;
    mBeaconManager = BeaconManager.getInstanceForApplication(getApplicationContext());
    mBeaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout(LLUNY_LAYOUT));
  }

  @Override public void scan(int seconds, Callback callback) {
    if (seconds < 0) {
      throw new RuntimeException("seconds cannot be < 0");
    }
    mSeconds = seconds;
    mCallback = callback;
    mBeaconManager.bind(this);
  }

  @Override public void didRangeBeaconsInRegion(Collection<Beacon> collection, Region region) {
    if (!mBeaconManager.isAnyConsumerBound()) {
      return;
    }

    mBeacons.addAll(collection);

    if (mSeconds <= 0) {
      mBeaconManager.unbind(this);
      mCleanRepeatedAltBeacon.clean(mBeacons);
      mCallback.onScanBeaconsSuccess(mLlunyEntityMapper.map(mBeacons));
    }

    mSeconds--;
  }

  @Override public void onBeaconServiceConnect() {
    mBeaconManager.setRangeNotifier(this);

    try {
      mBeaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
    } catch (RemoteException e) {
      //TODO(david) The exception should be propagated
      mCallback.onScanBeaconsError();
      e.printStackTrace();
    }
  }

  @Override public Context getApplicationContext() {
    return mContext.getApplicationContext();
  }

  @Override public void unbindService(ServiceConnection serviceConnection) {
    mContext.unbindService(serviceConnection);
  }

  @Override
  public boolean bindService(Intent intent, ServiceConnection serviceConnection, int flags) {
    return mContext.bindService(intent, serviceConnection, flags);
  }
}
