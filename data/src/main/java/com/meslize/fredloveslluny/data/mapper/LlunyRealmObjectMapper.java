package com.meslize.fredloveslluny.data.mapper;

import com.meslize.fredloveslluny.data.datasource.realm.entity.LlunyRealmObject;
import com.meslize.fredloveslluny.data.entity.LlunyEntity;

public class LlunyRealmObjectMapper {
  public LlunyRealmObject map(LlunyEntity data) {
    LlunyRealmObject result = new LlunyRealmObject();
    result.setId(data.getId());
    result.setName(data.getName());
    result.setDistance(data.getDistance());

    return result;
  }
}
