package com.meslize.fredloveslluny.data.mapper;

import com.meslize.fredloveslluny.data.entity.LlunyEntity;
import com.meslize.fredloveslluny.domain.object.Lluny;
import java.util.ArrayList;
import java.util.List;

public class LlunyMapper {
  public Lluny map(LlunyEntity data) {
    return new Lluny.Builder().setId(data.getId())
        .setName(data.getName())
        .setDistance(data.getDistance())
        .build();
  }

  public List<Lluny> map(List<LlunyEntity> data) {
    List<Lluny> result = new ArrayList<>(data.size());

    for (LlunyEntity item : data) {
      result.add(map(item));
    }

    return result;
  }
}
