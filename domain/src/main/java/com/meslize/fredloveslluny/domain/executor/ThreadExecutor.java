/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 */

package com.meslize.fredloveslluny.domain.executor;

import com.meslize.fredloveslluny.domain.usecase.UseCase;

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the {@link UseCase} out of the UI thread.
 * <p/>
 * Use this class to execute an {@link UseCase}.
 */
public interface ThreadExecutor {
  /**
   * Executes a {@link Runnable}.
   *
   * @param runnable The class that implements {@link Runnable} interface.
   */
  void execute(final Runnable runnable);
}
