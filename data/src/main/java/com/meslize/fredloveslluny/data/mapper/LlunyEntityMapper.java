package com.meslize.fredloveslluny.data.mapper;

import com.meslize.fredloveslluny.data.datasource.realm.entity.LlunyRealmObject;
import com.meslize.fredloveslluny.data.entity.LlunyEntity;
import com.meslize.fredloveslluny.domain.object.Lluny;
import io.realm.RealmResults;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.altbeacon.beacon.Beacon;

public class LlunyEntityMapper {
  public LlunyEntity map(Lluny data) {
    return new LlunyEntity.Builder().setId(data.getId())
        .setName(data.getName())
        .setDistance(data.getDistance())
        .build();
  }

  public LlunyEntity[] map(Lluny... data) {
    LlunyEntity[] result = new LlunyEntity[data.length];
    for (int i = 0; i < data.length; i++) {
      result[i] = map(data[i]);
    }

    return result;
  }

  public LlunyEntity map(LlunyRealmObject data) {
    return new LlunyEntity.Builder().setId(data.getId())
        .setName(data.getName())
        .setDistance(data.getDistance())
        .build();
  }

  public List<LlunyEntity> map(RealmResults<LlunyRealmObject> data) {
    List<LlunyEntity> result = new ArrayList<>();

    for (LlunyRealmObject item : data) {
      result.add(map(item));
    }

    return result;
  }

  public LlunyEntity map(Beacon data) {
    return new LlunyEntity.Builder().setId(data.getId1().toString())
        .setName("")
        .setDistance(data.getDistance())
        .build();
  }

  public List<LlunyEntity> map(Collection<Beacon> data) {
    List<LlunyEntity> result = new ArrayList<>(data.size());
    for (Beacon item : data) {
      result.add(map(item));
    }

    return result;
  }
}
