package com.trov.fred;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.BeaconTransmitter;

import java.util.Arrays;

public class TransmittingActivity extends AppCompatActivity {

    public static final String ALT_BEACON_LAYOUT = "m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25";
    public static final String ID_1 = "2f234454-cf6d-4a0f-adf2-f4911ba9ffa6";
    public static final String ID_2 = "1";
    public static final String ID_3 = "2";
    protected static final String TAG = TransmittingActivity.class.getName();
    private BeaconTransmitter mBeaconTransmitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Beacon beacon = new Beacon.Builder().setId1(ID_1)
                                            .setId2(ID_2)
                                            .setId3(ID_3)
                                            .setManufacturer(0x0118)
                                            .setTxPower(-59)
                                            .setDataFields(Arrays.asList(new Long[] {0l}))
                                            .build();
        BeaconParser beaconParser = new BeaconParser().setBeaconLayout(ALT_BEACON_LAYOUT);
        mBeaconTransmitter = new BeaconTransmitter(getApplicationContext(), beaconParser);
        mBeaconTransmitter.startAdvertising(beacon);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBeaconTransmitter.stopAdvertising();
    }
}
