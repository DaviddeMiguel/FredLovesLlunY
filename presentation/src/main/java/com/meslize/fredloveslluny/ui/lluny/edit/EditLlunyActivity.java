package com.meslize.fredloveslluny.ui.lluny.edit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import butterknife.Bind;
import butterknife.OnClick;
import com.meslize.fredloveslluny.R;
import com.meslize.fredloveslluny.internal.di.injectors.EditLlunyInjector;
import com.meslize.fredloveslluny.ui.lluny.edit.presenter.EditLlunyPresenter;
import com.meslize.fredloveslluny.util.BaseActivity;
import javax.inject.Inject;

public class EditLlunyActivity extends BaseActivity implements EditLlunyView {

  EditLlunyInjector mInjector = new EditLlunyInjector();

  @Inject EditLlunyPresenter mPresenter;

  @Bind(R.id.toolbar) Toolbar mToolbar;
  @Bind(R.id.fab) FloatingActionButton mFloatingActionButton;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_lluny);
    mInjector.inject(this);

    setSupportActionBar(mToolbar);

    mPresenter.bind(this);
  }

  @OnClick(R.id.save) void onClickSave() {
    mPresenter.onClickSave();
  }

  @Override protected void onDestroy() {
    mPresenter.unbind();
    super.onDestroy();
  }

  @Override public void setForwardedResultResult() {
    setResult(RESULT_OK);
    finish();
  }

  public static class Navigator {
    public void start(Context context) {
      Intent intent = new Intent(context, EditLlunyActivity.class);
      intent.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
      context.startActivity(intent);
    }
  }
}
