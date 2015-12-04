package com.meslize.fredloveslluny.domain.usecase;

import com.meslize.fredloveslluny.domain.executor.PostExecutionThread;
import com.meslize.fredloveslluny.domain.executor.ThreadExecutor;
import com.meslize.fredloveslluny.domain.object.Lluny;
import com.meslize.fredloveslluny.domain.repository.LlunyRepository;

public class AddLlunyUseCaseImpl extends UseCase implements AddLlunyUseCase {

  final LlunyRepository llunyRepository;

  Lluny lluny;
  Callback callback;

  public AddLlunyUseCaseImpl(LlunyRepository llunyRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);

    this.llunyRepository = llunyRepository;
  }

  @Override public void run() {
    llunyRepository.add(lluny);
    onPostExecute(new Runnable() {
      @Override public void run() {
        callback.onAddLlunySuccess();
      }
    });
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
