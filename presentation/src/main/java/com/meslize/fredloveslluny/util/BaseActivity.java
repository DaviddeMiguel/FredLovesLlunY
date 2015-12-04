package com.meslize.fredloveslluny.util;

import android.support.v7.app.AppCompatActivity;
import com.meslize.fredloveslluny.AndroidApplication;
import com.meslize.fredloveslluny.internal.di.components.ApplicationComponent;
import com.meslize.fredloveslluny.internal.di.modules.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity {
  protected ApplicationComponent getApplicationComponent() {
    return ((AndroidApplication) getApplication()).getApplicationComponent();
  }

  protected ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }
}
