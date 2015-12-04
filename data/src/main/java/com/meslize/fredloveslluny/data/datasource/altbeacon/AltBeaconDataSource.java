package com.meslize.fredloveslluny.data.datasource.altbeacon;

import com.meslize.fredloveslluny.data.entity.LlunyEntity;
import java.util.List;

public interface AltBeaconDataSource {
  void scan(int seconds, Callback callback);

  interface Callback {
    void onScanBeaconsSuccess(final List<LlunyEntity> items);
    void onScanBeaconsError();
  }
}
