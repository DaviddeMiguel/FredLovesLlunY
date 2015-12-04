package com.meslize.fredloveslluny.domain.usecase;

import com.meslize.fredloveslluny.domain.executor.PostExecutionThread;
import com.meslize.fredloveslluny.domain.executor.ThreadExecutor;

public abstract class UseCase implements Runnable {

  final ThreadExecutor threadExecutor;
  final PostExecutionThread postExecutionThread;

  public UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
    this.threadExecutor = threadExecutor;
    this.postExecutionThread = postExecutionThread;
  }

  protected void execute() {
    threadExecutor.execute(this);
  }

  protected void onPostExecute(Runnable runnable){
    postExecutionThread.post(runnable);
  }
}
