package com.meslize.fredloveslluny.internal.di.components;

import android.app.Activity;
import com.meslize.fredloveslluny.internal.di.PerActivity;
import com.meslize.fredloveslluny.internal.di.modules.ActivityModule;
import dagger.Component;

@PerActivity @Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
  Activity activity();
}
