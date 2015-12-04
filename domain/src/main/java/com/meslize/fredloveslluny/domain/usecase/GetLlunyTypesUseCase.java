package com.meslize.fredloveslluny.domain.usecase;

public interface GetLlunyTypesUseCase {

  void execute(Callback callback);

  void executeAsync(Callback callback);

  interface Callback {
    void onGetLlunyTypesSuccess();
  }
}
