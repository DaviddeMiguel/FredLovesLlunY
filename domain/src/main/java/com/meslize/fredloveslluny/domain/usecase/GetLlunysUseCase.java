package com.meslize.fredloveslluny.domain.usecase;

import com.meslize.fredloveslluny.domain.object.Lluny;
import java.util.List;

public interface GetLlunysUseCase {

  void execute(Callback callback);

  void executeAsync(Callback callback);

  interface Callback {
    void onGetLlunysSuccess(List<Lluny> data);
  }
}
