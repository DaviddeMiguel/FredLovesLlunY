package com.meslize.fredloveslluny.data.repository;

import com.meslize.fredloveslluny.data.datasource.altbeacon.AltBeaconDataSource;
import com.meslize.fredloveslluny.data.datasource.realm.RealmDataSource;
import com.meslize.fredloveslluny.data.entity.LlunyEntity;
import com.meslize.fredloveslluny.data.mapper.LlunyEntityMapper;
import com.meslize.fredloveslluny.data.mapper.LlunyMapper;
import com.meslize.fredloveslluny.domain.object.Lluny;
import com.meslize.fredloveslluny.domain.repository.LlunyRepository;
import java.util.List;

public class LlunyRepositoryImpl implements LlunyRepository {
  final AltBeaconDataSource mAltBeaconDataSource;
  final RealmDataSource mRealmDataSource;

  LlunyMapper mLlunyMapper = new LlunyMapper();
  LlunyEntityMapper mLlunyEntityMapper = new LlunyEntityMapper();

  public LlunyRepositoryImpl(AltBeaconDataSource altBeaconDataSource,
      RealmDataSource realmDataSource) {
    mAltBeaconDataSource = altBeaconDataSource;
    mRealmDataSource = realmDataSource;
  }

  @Override public void getAll(final Callback callback) {
    callback.onSuccess(mLlunyMapper.map(mRealmDataSource.findAll()));
  }

  @Override public void scanBeacons(int seconds, final Callback callback) {
    mAltBeaconDataSource.scan(seconds, new AltBeaconDataSource.Callback() {
      @Override public void onScanBeaconsSuccess(List<LlunyEntity> items) {
        callback.onSuccess(mLlunyMapper.map(items));
      }

      @Override public void onScanBeaconsError() {
        callback.onError();
      }
    });
  }

  @Override public void add(Lluny... items) {
    mRealmDataSource.add(mLlunyEntityMapper.map(items));
  }
}
