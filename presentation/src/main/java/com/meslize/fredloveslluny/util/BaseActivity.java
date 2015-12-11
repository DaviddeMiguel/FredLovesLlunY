package com.meslize.fredloveslluny.util;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import com.meslize.fredloveslluny.AndroidApplication;
import com.meslize.fredloveslluny.internal.di.components.ApplicationComponent;
import com.meslize.fredloveslluny.internal.di.modules.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity {

  public static final int REQUEST_CODE_ADD_LLUNY = 1000;

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (RESULT_OK == resultCode) {
      setResult(resultCode, data);
      finish();
    }
  }

  protected ApplicationComponent getApplicationComponent() {
    return ((AndroidApplication) getApplication()).getApplicationComponent();
  }

  protected ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }
}
