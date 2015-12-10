package com.meslize.fredloveslluny.internal.di.injectors;

import butterknife.ButterKnife;
import com.meslize.fredloveslluny.AndroidApplication;
import com.meslize.fredloveslluny.internal.di.components.ApplicationComponent;
import com.meslize.fredloveslluny.internal.di.components.DaggerLlunyComponent;
import com.meslize.fredloveslluny.internal.di.components.LlunyComponent;
import com.meslize.fredloveslluny.internal.di.modules.ActivityModule;
import com.meslize.fredloveslluny.ui.lluny.edit.EditLlunyActivity;

public class EditLlunyInjector {
  public void inject(EditLlunyActivity activity) {
    ApplicationComponent applicationComponent =
        AndroidApplication.getInstance().getApplicationComponent();
    ActivityModule activityModule = new ActivityModule(activity);

    LlunyComponent component = DaggerLlunyComponent.builder()
        .applicationComponent(applicationComponent)
        .activityModule(activityModule)
        .build();

    component.inject(activity);

    ButterKnife.bind(activity);
  }
}
