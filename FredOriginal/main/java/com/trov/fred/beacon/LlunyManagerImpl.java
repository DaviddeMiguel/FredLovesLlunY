package com.trov.fred.beacon;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.RemoteException;

import com.trov.fred.FredApplication;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;

public class LlunyManagerImpl implements LlunyManager, BeaconConsumer {

    static final String LLUNY_LAYOUT = "m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24";

    Context mContext = FredApplication.getInstance();
    Handler mHandler = new Handler(mContext.getMainLooper());

    BeaconManager mBeaconManager;
    Callback mCallback;

    public LlunyManagerImpl() {
        mBeaconManager = BeaconManager.getInstanceForApplication(mContext);

        // To detect proprietary beacons, you must add a line like below corresponding to your beacon
        mBeaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout(LLUNY_LAYOUT));
    }

    @Override
    public void start(Callback callback) {
        mCallback = callback;

        mBeaconManager.bind(this);
    }

    @Override
    public void stop() {
        mBeaconManager.unbind(this);
    }

    @Override
    public void onBeaconServiceConnect() {
        mBeaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(final Collection<Beacon> beacons, final Region region) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mCallback.onBeaconsDetected(beacons, region);
                    }
                });
            }
        });

        try {
            mBeaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Context getApplicationContext() {
        return mContext;
    }

    @Override
    public void unbindService(ServiceConnection serviceConnection) {
        mContext.unbindService(serviceConnection);
    }

    @Override
    public boolean bindService(Intent intent, ServiceConnection serviceConnection, int flags) {
        return mContext.bindService(intent, serviceConnection, flags);
    }
}
