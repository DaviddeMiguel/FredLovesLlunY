package com.meslize.fredloveslluny.data.datasource.realm;

import com.meslize.fredloveslluny.data.entity.LlunyEntity;
import java.util.List;

public interface RealmDataSource {
  List<LlunyEntity> findAll();

  void add(LlunyEntity... items);
}
