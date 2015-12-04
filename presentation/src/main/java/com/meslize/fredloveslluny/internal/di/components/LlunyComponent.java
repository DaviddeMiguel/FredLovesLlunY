package com.meslize.fredloveslluny.internal.di.components;

import com.meslize.fredloveslluny.internal.di.PerActivity;
import com.meslize.fredloveslluny.internal.di.modules.ActivityModule;
import com.meslize.fredloveslluny.internal.di.modules.LlunyModule;
import com.meslize.fredloveslluny.ui.lluny.list.view.LlunyListActivity;
import com.meslize.fredloveslluny.ui.scan.list.view.ScanListActivity;
import dagger.Component;

@PerActivity @Component(dependencies = ApplicationComponent.class, modules = {
    ActivityModule.class, LlunyModule.class
}) public interface LlunyComponent extends ActivityComponent {
  void inject(LlunyListActivity activity);

  void inject(ScanListActivity activity);
}
