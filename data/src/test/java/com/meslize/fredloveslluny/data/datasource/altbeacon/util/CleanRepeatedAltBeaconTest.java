package com.meslize.fredloveslluny.data.datasource.altbeacon.util;

import java.util.ArrayList;
import java.util.List;
import org.altbeacon.beacon.Beacon;
import org.junit.Assert;
import org.junit.Test;

public class CleanRepeatedAltBeaconTest {

  private static final String ID_1 = "f7826da6-4fa2-4e98-8024-bc5b71e08931";
  private static final String ID_2 = "f7826da6-4fa2-4e98-8024-bc5b71e08932";
  private static final String ID_3 = "f7826da6-4fa2-4e98-8024-bc5b71e08933";

  @Test public void testCleanRemovesRepeatedItems() throws Exception {
    List<Beacon> items = givenListWithRepeatedItems();

    CleanRepeatedAltBeacon cleanRepeatedAltBeacon = new CleanRepeatedAltBeacon();
    cleanRepeatedAltBeacon.clean(items);

    Assert.assertEquals(new Beacon.Builder().setId1(ID_1).build(), items.get(0));
    Assert.assertEquals(new Beacon.Builder().setId1(ID_2).build(), items.get(1));
    Assert.assertEquals(new Beacon.Builder().setId1(ID_3).build(), items.get(2));
  }

  private List<Beacon> givenListWithRepeatedItems() {
    List<Beacon> items = new ArrayList<>();
    items.add(new Beacon.Builder().setId1(ID_1).build());
    items.add(new Beacon.Builder().setId1(ID_2).build());
    items.add(new Beacon.Builder().setId1(ID_1).build());
    items.add(new Beacon.Builder().setId1(ID_3).build());
    items.add(new Beacon.Builder().setId1(ID_2).build());

    return items;
  }
}
