package com.meslize.fredloveslluny.domain.repository;

import com.meslize.fredloveslluny.domain.object.Lluny;
import java.util.List;

public interface LlunyRepository {
  void getAll(Callback callback);

  void scanBeacons(int seconds, Callback callback);

  void add(Lluny... items);

  interface Callback {
    void onSuccess(List<Lluny> items);

    void onError();
  }
}
