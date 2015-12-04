package com.meslize.fredloveslluny.mapper;

import com.meslize.fredloveslluny.domain.object.Lluny;
import com.meslize.fredloveslluny.model.LlunyModel;
import java.util.ArrayList;
import java.util.List;

public class LlunyModelMapper {
  public LlunyModel map(Lluny data) {
    return new LlunyModel.Builder().setId(data.getId())
        .setName(data.getName())
        .setDistance(data.getDistance())
        .build();
  }

  public List<LlunyModel> map(List<Lluny> data) {
    List<LlunyModel> result = new ArrayList<>(data.size());

    for (Lluny item : data) {
      result.add(map(item));
    }

    return result;
  }
}
