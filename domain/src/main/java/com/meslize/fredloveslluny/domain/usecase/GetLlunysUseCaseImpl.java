package com.meslize.fredloveslluny.domain.usecase;

import com.meslize.fredloveslluny.domain.executor.PostExecutionThread;
import com.meslize.fredloveslluny.domain.executor.ThreadExecutor;
import com.meslize.fredloveslluny.domain.object.Lluny;
import com.meslize.fredloveslluny.domain.repository.LlunyRepository;
import java.util.List;

public class GetLlunysUseCaseImpl extends UseCase implements GetLlunysUseCase {

  final LlunyRepository llunyRepository;

  Callback callback;

  public GetLlunysUseCaseImpl(LlunyRepository llunyRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);

    this.llunyRepository = llunyRepository;
  }

  @Override public void run() {
    llunyRepository.getAll(new LlunyRepository.Callback() {
      @Override public void onSuccess(final List<Lluny> items) {
        onPostExecute(new Runnable() {
          @Override public void run() {
            callback.onGetLlunysSuccess(items);
          }
        });
      }

      @Override public void onError() {
        //empty
      }
    });
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
