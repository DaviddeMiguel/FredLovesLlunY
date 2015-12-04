package com.meslize.fredloveslluny.domain.usecase;

import com.meslize.fredloveslluny.domain.object.Lluny;
import com.meslize.fredloveslluny.domain.executor.PostExecutionThread;
import com.meslize.fredloveslluny.domain.executor.ThreadExecutor;
import com.meslize.fredloveslluny.domain.repository.LlunyRepository;

public class UpdateLlunyUseCaseImpl extends UseCase implements UpdateLlunyUseCase {

  final LlunyRepository llunyRepository;

  Lluny lluny;
  Callback callback;

  public UpdateLlunyUseCaseImpl(LlunyRepository llunyRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);

    this.llunyRepository = llunyRepository;
  }

  @Override public void run() {

  }

  @Override public void execute(Lluny lluny, Callback callback) {
    this.lluny = lluny;
    this.callback = callback;
    run();
  }

  @Override public void executeAsync(Lluny lluny, Callback callback) {
    this.lluny = lluny;
    this.callback = callback;
    execute();
  }
}
