package com.trov.fred;

import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;

public class BeaconManagerExample extends AppCompatActivity implements BeaconConsumer {

    protected static final String TAG = BeaconManagerExample.class.getName();
    static final String I_BEACON_LAYOUT = "m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24";

    Handler mHandler = new Handler();
    private BeaconManager mBeaconManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBeaconManager = BeaconManager.getInstanceForApplication(this);
        // To detect proprietary beacons, you must add a line like below corresponding to your beacon
        // type.  Do a web search for "setBeaconLayout" to get the proper expression.
        mBeaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout(I_BEACON_LAYOUT));
        mBeaconManager.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBeaconManager.unbind(this);
    }

    @Override
    public void onBeaconServiceConnect() {
        mBeaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(final Collection<Beacon> beacons, Region region) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("BEACON MANAGER", "Number detected: " + beacons.size());

                        if (beacons.size() > 0) {
                            Beacon beacon = beacons.iterator().next();

                            String id = beacon.getId1().toString();
                            String distance = String.valueOf(beacon.getDistance());

                            Log.d("BEACON MANAGER", "ID: " + id);
                            Log.d("BEACON MANAGER", "Distance: " + distance);
                        }
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
}
