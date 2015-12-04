package com.meslize.fredloveslluny.internal.di.components;

import android.content.Context;
import com.meslize.fredloveslluny.domain.executor.PostExecutionThread;
import com.meslize.fredloveslluny.domain.executor.ThreadExecutor;
import com.meslize.fredloveslluny.domain.repository.LlunyRepository;
import com.meslize.fredloveslluny.internal.di.modules.ApplicationModule;
import com.meslize.fredloveslluny.util.BaseActivity;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = ApplicationModule.class) public interface ApplicationComponent {
  void inject(BaseActivity baseActivity);

  Context context();

  ThreadExecutor threadExecutor();

  PostExecutionThread postExecutionThread();

  LlunyRepository llunyRepository();
}
