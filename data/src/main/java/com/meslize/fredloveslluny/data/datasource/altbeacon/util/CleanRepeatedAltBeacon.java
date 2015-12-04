package com.meslize.fredloveslluny.data.datasource.altbeacon.util;

import java.util.List;
import org.altbeacon.beacon.Beacon;

public class CleanRepeatedAltBeacon {
  public void clean(List<Beacon> items) {
    int i = 0;
    int j;
    Beacon item;

    while (i < items.size()) {
      item = items.get(i);

      j = i + 1;

      while (j < items.size()) {
        if (item.equals(items.get(j))) {
          items.remove(j);
        }
        j++;
      }

      i++;
    }
  }
}
