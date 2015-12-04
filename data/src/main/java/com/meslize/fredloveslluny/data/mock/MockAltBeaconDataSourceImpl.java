package com.meslize.fredloveslluny.data.mock;

import android.content.Context;
import com.meslize.fredloveslluny.data.datasource.altbeacon.AltBeaconDataSource;
import com.meslize.fredloveslluny.data.entity.LlunyEntity;
import java.util.ArrayList;
import java.util.List;

public class MockAltBeaconDataSourceImpl implements AltBeaconDataSource {

  public MockAltBeaconDataSourceImpl(Context context) {

  }

  @Override public void scan(int seconds, Callback callback) {
    List<LlunyEntity> result = new ArrayList<>();

    String id;
    String name;
    double distance;

    for (int i = 0; i < 20; i++) {
      id = String.valueOf(i);
      name = "Bluetooth: " + i;
      distance = i * 1.5;

      result.add(new LlunyEntity.Builder().setId(id).setName(name).setDistance(distance).build());
    }

    callback.onScanBeaconsSuccess(result);
  }
}
