package com.meslize.fredloveslluny.ui.scan.list.view;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import butterknife.Bind;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.meslize.fredloveslluny.R;
import com.meslize.fredloveslluny.internal.di.injectors.DetectListInjector;
import com.meslize.fredloveslluny.model.LlunyModel;
import com.meslize.fredloveslluny.ui.scan.list.presenter.ScanListPresenter;
import com.meslize.fredloveslluny.util.BaseActivity;
import com.meslize.fredloveslluny.util.BaseRecyclerViewAdapter;
import java.util.List;
import javax.inject.Inject;

public class ScanListActivity extends BaseActivity
    implements ScanListView, BaseRecyclerViewAdapter.OnItemClickListener {

  public static final int REQUEST_CODE = 1000;

  DetectListInjector mInjector = new DetectListInjector();

  @Inject ScanListPresenter mPresenter;

  @Bind(R.id.toolbar) Toolbar mToolbar;
  @Bind(R.id.recycler_view) RecyclerView mRecyclerView;

  ProgressDialog mProgressDialog;

  ScanListAdapter mAdapter = new ScanListAdapter();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detect_list);
    mInjector.inject(this);

    setSupportActionBar(mToolbar);

    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    mAdapter.setOnItemClickListener(this);

    Dexter.checkPermission(new PermissionListener() {
      @Override public void onPermissionGranted(PermissionGrantedResponse response) {
        mPresenter.bind(ScanListActivity.this);
      }

      @Override public void onPermissionDenied(PermissionDeniedResponse response) {
        Snackbar.make(mRecyclerView, "We need the bluetooth permission", Snackbar.LENGTH_LONG)
            .setAction("Undo", null)
            .show();
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

  @Override public void showItemAddedMessage() {
    Snackbar.make(mRecyclerView, "Item added", Snackbar.LENGTH_LONG).setAction("Undo", null).show();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_scan_list, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_scan) {
      mPresenter.actionScan();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override public void onClickItem(int position, View v) {
    Snackbar.make(mRecyclerView, "Item clicked: " + position, Snackbar.LENGTH_LONG).show();
  }

  public static class Navigator {
    public void startForResult(Activity activity) {
      Intent intent = new Intent(activity, ScanListActivity.class);
      activity.startActivityForResult(intent, REQUEST_CODE);
    }
  }
}
