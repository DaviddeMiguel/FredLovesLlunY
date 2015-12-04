package com.meslize.fredloveslluny.data.datasource.realm;

import android.content.Context;
import com.meslize.fredloveslluny.data.datasource.realm.entity.LlunyRealmObject;
import com.meslize.fredloveslluny.data.entity.LlunyEntity;
import com.meslize.fredloveslluny.data.mapper.LlunyEntityMapper;
import com.meslize.fredloveslluny.data.mapper.LlunyRealmObjectMapper;
import io.realm.Realm;
import java.util.List;

public class RealmDataSourceImpl implements RealmDataSource {
  final Context mContext;
  Realm mRealm;

  LlunyEntityMapper mLlunyEntityMapper = new LlunyEntityMapper();
  LlunyRealmObjectMapper mLlunyRealmObjectMapper = new LlunyRealmObjectMapper();

  public RealmDataSourceImpl(Context context) {
    mContext = context;
  }

  @Override public List<LlunyEntity> findAll() {
    Realm realm = Realm.getInstance(mContext);
    return mLlunyEntityMapper.map(realm.where(LlunyRealmObject.class).findAll());
  }

  @Override public void add(LlunyEntity... items) {
    Realm realm = Realm.getInstance(mContext);
    realm.beginTransaction();

    for (LlunyEntity item : items) {
      realm.copyToRealmOrUpdate(mLlunyRealmObjectMapper.map(item));
    }

    realm.commitTransaction();
  }
}
