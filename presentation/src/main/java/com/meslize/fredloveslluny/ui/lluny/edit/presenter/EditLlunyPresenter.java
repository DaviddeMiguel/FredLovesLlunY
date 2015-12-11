package com.meslize.fredloveslluny.ui.lluny.edit.presenter;

import android.support.annotation.NonNull;
import com.meslize.fredloveslluny.domain.object.Lluny;
import com.meslize.fredloveslluny.domain.usecase.AddLlunyUseCase;
import com.meslize.fredloveslluny.internal.di.PerActivity;
import com.meslize.fredloveslluny.mapper.LlunyModelMapper;
import com.meslize.fredloveslluny.ui.lluny.edit.EditLlunyView;
import javax.inject.Inject;

@PerActivity public class EditLlunyPresenter implements AddLlunyUseCase.Callback {

  AddLlunyUseCase mAddLlunyUseCase;

  EditLlunyView mView;

  LlunyModelMapper mLlunyModelMapper = new LlunyModelMapper();

  Lluny mLLuny;

  @Inject public EditLlunyPresenter(AddLlunyUseCase addLlunyUseCase) {
    mAddLlunyUseCase = addLlunyUseCase;
  }

  public void bind(@NonNull EditLlunyView view) {
    mView = view;
  }

  public void unbind() {
    mView = null;
  }

  @Override public void onAddLlunySuccess() {
    if (mView == null) {
      return;
    }

    mView.setForwardedResultResult();
  }

  public void onClickSave() {
    //TODO(david) We should save the lluny selected in db
    //mAddLlunyUseCase.executeAsync(mLLuny, this);
    mView.setForwardedResultResult();
  }
}
