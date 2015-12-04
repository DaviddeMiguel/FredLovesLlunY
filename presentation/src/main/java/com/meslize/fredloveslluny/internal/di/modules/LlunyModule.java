package com.meslize.fredloveslluny.internal.di.modules;

import com.meslize.fredloveslluny.domain.executor.PostExecutionThread;
import com.meslize.fredloveslluny.domain.executor.ThreadExecutor;
import com.meslize.fredloveslluny.domain.repository.LlunyRepository;
import com.meslize.fredloveslluny.domain.usecase.AddLlunyUseCase;
import com.meslize.fredloveslluny.domain.usecase.AddLlunyUseCaseImpl;
import com.meslize.fredloveslluny.domain.usecase.GetLlunyTypesUseCase;
import com.meslize.fredloveslluny.domain.usecase.GetLlunyTypesUseCaseImpl;
import com.meslize.fredloveslluny.domain.usecase.GetLlunysUseCase;
import com.meslize.fredloveslluny.domain.usecase.GetLlunysUseCaseImpl;
import com.meslize.fredloveslluny.domain.usecase.ScanBeaconsUseCase;
import com.meslize.fredloveslluny.domain.usecase.ScanBeaconsUseCaseImpl;
import com.meslize.fredloveslluny.domain.usecase.UpdateLlunyUseCase;
import com.meslize.fredloveslluny.domain.usecase.UpdateLlunyUseCaseImpl;
import com.meslize.fredloveslluny.internal.di.PerActivity;
import dagger.Module;
import dagger.Provides;

@Module public class LlunyModule {

  public LlunyModule() {
  }

  @Provides @PerActivity AddLlunyUseCase provideAddLlunyUseCase(LlunyRepository llunyRepository,
      ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
    return new AddLlunyUseCaseImpl(llunyRepository, threadExecutor, postExecutionThread);
  }

  @Provides @PerActivity ScanBeaconsUseCase provideScanBeaconsUseCase(
      LlunyRepository llunyRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    return new ScanBeaconsUseCaseImpl(llunyRepository, threadExecutor, postExecutionThread);
  }

  @Provides @PerActivity GetLlunysUseCase provideGetLlunysUseCase(LlunyRepository llunyRepository,
      ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
    return new GetLlunysUseCaseImpl(llunyRepository, threadExecutor, postExecutionThread);
  }

  @Provides @PerActivity GetLlunyTypesUseCase provideGetLlunyTypesUseCase(
      LlunyRepository llunyRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    return new GetLlunyTypesUseCaseImpl(llunyRepository, threadExecutor, postExecutionThread);
  }

  @Provides @PerActivity UpdateLlunyUseCase provideUpdateLlunyUseCase(
      LlunyRepository llunyRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    return new UpdateLlunyUseCaseImpl(llunyRepository, threadExecutor, postExecutionThread);
  }
}
