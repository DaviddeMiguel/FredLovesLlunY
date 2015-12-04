package com.trov.fred.beacon;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.Region;

import java.util.Collection;

public interface LlunyManager {
    void start(Callback callback);

    void stop();

    interface Callback {
        void onBeaconsDetected(final Collection<Beacon> beacons, Region region);
    }
}
