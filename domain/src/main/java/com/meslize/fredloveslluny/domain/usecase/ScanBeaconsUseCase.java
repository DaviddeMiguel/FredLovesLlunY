package com.meslize.fredloveslluny.domain.usecase;

import com.meslize.fredloveslluny.domain.object.Lluny;
import java.util.List;

public interface ScanBeaconsUseCase {

  void execute(int seconds, Callback callback);

  void executeAsync(int seconds, Callback callback);

  interface Callback {
    void onDetectBeaconsSuccess(List<Lluny> data);
  }
}
