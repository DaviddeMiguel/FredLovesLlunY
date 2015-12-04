/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.meslize.fredloveslluny;

import android.app.Application;
import com.karumi.dexter.Dexter;
import com.meslize.fredloveslluny.internal.di.components.ApplicationComponent;
import com.meslize.fredloveslluny.internal.di.components.DaggerApplicationComponent;
import com.meslize.fredloveslluny.internal.di.mock.DaggerMockApplicationComponent;
import com.meslize.fredloveslluny.internal.di.mock.MockApplicationModule;
import com.meslize.fredloveslluny.internal.di.modules.ApplicationModule;

public class AndroidApplication extends Application {

  private static AndroidApplication mInstance;

  private ApplicationComponent mApplicationComponent;

  public static AndroidApplication getInstance() {
    return mInstance;
  }

  @Override public void onCreate() {
    super.onCreate();
    mInstance = this;

    Dexter.initialize(this);

    initializeInjector();
  }

  private void initializeInjector() {
    if (BuildConfig.MOCK) {
      mApplicationComponent = DaggerMockApplicationComponent.builder()
          .mockApplicationModule(new MockApplicationModule(this))
          .build();
    } else {
      mApplicationComponent = DaggerApplicationComponent.builder()
          .applicationModule(new ApplicationModule(this))
          .build();
    }
  }

  public ApplicationComponent getApplicationComponent() {
    return this.mApplicationComponent;
  }
}