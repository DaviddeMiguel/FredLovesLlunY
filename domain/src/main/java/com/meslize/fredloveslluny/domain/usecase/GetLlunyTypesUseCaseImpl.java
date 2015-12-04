package com.meslize.fredloveslluny.domain.usecase;

import com.meslize.fredloveslluny.domain.executor.PostExecutionThread;
import com.meslize.fredloveslluny.domain.executor.ThreadExecutor;
import com.meslize.fredloveslluny.domain.repository.LlunyRepository;

public class GetLlunyTypesUseCaseImpl extends UseCase implements GetLlunyTypesUseCase {

  final LlunyRepository llunyRepository;

  Callback callback;

  public GetLlunyTypesUseCaseImpl(LlunyRepository llunyRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);

    this.llunyRepository = llunyRepository;
  }

  @Override public void run() {

  }

  @Override public void execute(Callback callback) {
    this.callback = callback;
    run();
  }

  @Override public void executeAsync(Callback callback) {
    this.callback = callback;
    execute();
  }
}
