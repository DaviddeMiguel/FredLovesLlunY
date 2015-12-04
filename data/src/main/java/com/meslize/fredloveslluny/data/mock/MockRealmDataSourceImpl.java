package com.meslize.fredloveslluny.data.mock;

import android.content.Context;
import com.meslize.fredloveslluny.data.datasource.realm.RealmDataSource;
import com.meslize.fredloveslluny.data.entity.LlunyEntity;
import java.util.ArrayList;
import java.util.List;

public class MockRealmDataSourceImpl implements RealmDataSource {
  public MockRealmDataSourceImpl(Context context) {

  }

  @Override public List<LlunyEntity> findAll() {
    List<LlunyEntity> result = new ArrayList<>();

    String id;
    String name;
    double distance;

    for (int i = 0; i < 20; i++) {
      id = String.valueOf(i);
      name = "Database: " + i;
      distance = i * 1.5;

      result.add(new LlunyEntity.Builder().setId(id).setName(name).setDistance(distance).build());
    }

    return result;
  }

  @Override public void add(LlunyEntity... items) {
    //empty
  }
}
