package com.meslize.fredloveslluny.ui.lluny.list.presenter;

import android.support.annotation.NonNull;
import com.meslize.fredloveslluny.domain.object.Lluny;
import com.meslize.fredloveslluny.domain.usecase.GetLlunysUseCase;
import com.meslize.fredloveslluny.internal.di.PerActivity;
import com.meslize.fredloveslluny.mapper.LlunyModelMapper;
import com.meslize.fredloveslluny.ui.lluny.list.view.LlunyListView;
import java.util.List;
import javax.inject.Inject;

@PerActivity public class LlunyListPresenter implements GetLlunysUseCase.Callback {

  GetLlunysUseCase mGetLlunysUseCase;

  LlunyListView mView;

  LlunyModelMapper mLlunyModelMapper = new LlunyModelMapper();

  @Inject public LlunyListPresenter(GetLlunysUseCase getLlunysUseCase) {
    mGetLlunysUseCase = getLlunysUseCase;
  }

  public void bind(@NonNull LlunyListView view) {
    mView = view;

    mGetLlunysUseCase.executeAsync(this);
  }

  public void unbind() {
    mView = null;
  }

  @Override public void onGetLlunysSuccess(List<Lluny> data) {
    if (mView == null) {
      return;
    }

    mView.showItems(mLlunyModelMapper.map(data));
  }

  public void onClickFab() {
    mView.startForResultDetectListScreen();
  }
}
