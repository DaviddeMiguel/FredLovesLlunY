package com.meslize.fredloveslluny.domain.usecase;

import com.meslize.fredloveslluny.domain.executor.PostExecutionThread;
import com.meslize.fredloveslluny.domain.executor.ThreadExecutor;
import com.meslize.fredloveslluny.domain.object.Lluny;
import com.meslize.fredloveslluny.domain.repository.LlunyRepository;
import java.util.List;

public class ScanBeaconsUseCaseImpl extends UseCase implements ScanBeaconsUseCase {

  final LlunyRepository llunyRepository;

  int seconds;
  Callback callback;

  public ScanBeaconsUseCaseImpl(LlunyRepository llunyRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);

    this.llunyRepository = llunyRepository;
  }

  @Override public void run() {
    llunyRepository.scanBeacons(seconds, new LlunyRepository.Callback() {
      @Override public void onSuccess(final List<Lluny> items) {
        onPostExecute(new Runnable() {
          @Override public void run() {
            callback.onDetectBeaconsSuccess(items);
          }
        });
      }

      @Override public void onError() {
        //TODO(david) The UI should get notified
      }
    });
  }

  @Override public void execute(int seconds, Callback callback) {
    this.seconds = seconds;
    this.callback = callback;
    run();
  }

  @Override public void executeAsync(int seconds, Callback callback) {
    this.seconds = seconds;
    this.callback = callback;
    execute();
  }
}
