package com.meslize.fredloveslluny.internal.di.modules;

import android.content.Context;
import com.meslize.fredloveslluny.AndroidApplication;
import com.meslize.fredloveslluny.data.datasource.altbeacon.AltBeaconDataSource;
import com.meslize.fredloveslluny.data.datasource.realm.RealmDataSource;
import com.meslize.fredloveslluny.data.mock.MockAltBeaconDataSourceImpl;
import com.meslize.fredloveslluny.data.mock.MockRealmDataSourceImpl;
import com.meslize.fredloveslluny.data.repository.LlunyRepositoryImpl;
import com.meslize.fredloveslluny.domain.executor.JobExecutor;
import com.meslize.fredloveslluny.domain.executor.PostExecutionThread;
import com.meslize.fredloveslluny.domain.executor.ThreadExecutor;
import com.meslize.fredloveslluny.domain.repository.LlunyRepository;
import com.meslize.fredloveslluny.util.UIThread;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public class ApplicationModule {
  private final AndroidApplication application;

  public ApplicationModule(AndroidApplication application) {
    this.application = application;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return this.application;
  }

  @Provides @Singleton ThreadExecutor provideThreadExecutor() {
    return new JobExecutor();
  }

  @Provides @Singleton PostExecutionThread providePostExecutionThread() {
    return new UIThread();
  }

  @Provides @Singleton AltBeaconDataSource provideAltBeaconDataSource(@Singleton Context context) {
    return new MockAltBeaconDataSourceImpl(context);
  }

  @Provides @Singleton RealmDataSource provideRealmDataSource(@Singleton Context context) {
    return new MockRealmDataSourceImpl(context);
  }

  @Provides @Singleton LlunyRepository provideLlunyRepository(
      AltBeaconDataSource altBeaconDataSource, RealmDataSource realmDataSource) {
    return new LlunyRepositoryImpl(altBeaconDataSource, realmDataSource);
  }
}
