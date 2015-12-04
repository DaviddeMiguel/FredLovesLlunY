package com.meslize.fredloveslluny.ui.lluny.list.view;

import android.Manifest;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import butterknife.Bind;
import butterknife.OnClick;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.meslize.fredloveslluny.R;
import com.meslize.fredloveslluny.internal.di.injectors.LlunyListInjector;
import com.meslize.fredloveslluny.model.LlunyModel;
import com.meslize.fredloveslluny.ui.lluny.list.presenter.LlunyListPresenter;
import com.meslize.fredloveslluny.ui.scan.list.view.ScanListActivity;
import com.meslize.fredloveslluny.util.BaseActivity;
import com.meslize.fredloveslluny.util.BaseRecyclerViewAdapter;
import java.util.List;
import javax.inject.Inject;

public class LlunyListActivity extends BaseActivity
    implements LlunyListView, BaseRecyclerViewAdapter.OnItemClickListener {

  LlunyListInjector mInjector = new LlunyListInjector();

  ScanListActivity.Navigator mDetectListNavigator = new ScanListActivity.Navigator();

  @Inject LlunyListPresenter mPresenter;

  @Bind(R.id.toolbar) Toolbar mToolbar;
  @Bind(R.id.fab) FloatingActionButton mFloatingActionButton;
  @Bind(R.id.recycler_view) RecyclerView mRecyclerView;

  LlunyListAdapter mAdapter = new LlunyListAdapter();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lluny_list);
    mInjector.inject(this);

    setSupportActionBar(mToolbar);

    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    mAdapter.setOnItemClickListener(this);

    mPresenter.bind(this);
  }

  @OnClick(R.id.fab) void onClickFab() {
    Dexter.checkPermission(new PermissionListener() {
      @Override public void onPermissionGranted(PermissionGrantedResponse response) {
        mPresenter.onClickFab();
      }

      @Override public void onPermissionDenied(PermissionDeniedResponse response) {
        Snackbar.make(mFloatingActionButton, "We need the bluetooth permission",
            Snackbar.LENGTH_LONG).setAction("Undo", null).show();
      }

      @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission,
          PermissionToken token) {

      }
    }, Manifest.permission.ACCESS_FINE_LOCATION);
  }

  @Override protected void onDestroy() {
    mPresenter.unbind();
    super.onDestroy();
  }

  @Override public void showItems(List<LlunyModel> items) {
    mAdapter.setItems(items);
    mRecyclerView.setAdapter(mAdapter);
  }

  @Override public void startForResultDetectListScreen() {
    mDetectListNavigator.startForResult(LlunyListActivity.this);
  }

  @Override public void onClickItem(int position, View v) {
    Snackbar.make(mRecyclerView, "Item clicked: " + position, Snackbar.LENGTH_LONG).show();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_lluny_list, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
