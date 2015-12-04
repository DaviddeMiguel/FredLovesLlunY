package com.meslize.fredloveslluny.domain.usecase;

import com.meslize.fredloveslluny.domain.object.Lluny;

public interface UpdateLlunyUseCase {

  void execute(Lluny lluny, Callback callback);

  void executeAsync(Lluny lluny, Callback callback);

  interface Callback {
    void onUpdateLlunySuccess();
  }
}
