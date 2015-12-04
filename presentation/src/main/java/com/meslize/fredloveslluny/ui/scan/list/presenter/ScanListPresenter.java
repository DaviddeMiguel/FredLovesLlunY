package com.meslize.fredloveslluny.ui.scan.list.presenter;

import android.support.annotation.NonNull;
import com.meslize.fredloveslluny.domain.object.Lluny;
import com.meslize.fredloveslluny.domain.usecase.AddLlunyUseCase;
import com.meslize.fredloveslluny.domain.usecase.ScanBeaconsUseCase;
import com.meslize.fredloveslluny.internal.di.PerActivity;
import com.meslize.fredloveslluny.mapper.LlunyModelMapper;
import com.meslize.fredloveslluny.ui.scan.list.view.ScanListView;
import java.util.List;
import javax.inject.Inject;

@PerActivity public class ScanListPresenter
    implements AddLlunyUseCase.Callback, ScanBeaconsUseCase.Callback {
  private static final int SCAN_SECONDS = 5;

  ScanBeaconsUseCase mScanBeaconsUseCase;
  AddLlunyUseCase mAddLlunyUseCase;

  ScanListView mView;

  LlunyModelMapper mLlunyModelMapper = new LlunyModelMapper();

  @Inject
  public ScanListPresenter(ScanBeaconsUseCase scanBeaconsUseCase, AddLlunyUseCase addLlunyUseCase) {
    mScanBeaconsUseCase = scanBeaconsUseCase;
    mAddLlunyUseCase = addLlunyUseCase;
  }

  public void bind(@NonNull ScanListView view) {
    mView = view;

    startScanning();
  }

  private void startScanning() {
    mScanBeaconsUseCase.executeAsync(SCAN_SECONDS, this);
  }

  public void unbind() {
    mView = null;
  }

  public void actionScan() {
    startScanning();
  }

  @Override public void onAddLlunySuccess() {
    if (mView == null) {
      return;
    }

    mView.showItemAddedMessage();
    //TODO(david) The screen should be closed at this point
  }

  @Override public void onDetectBeaconsSuccess(List<Lluny> data) {
    if (mView == null) {
      return;
    }

    mView.showItems(mLlunyModelMapper.map(data));
  }
}
