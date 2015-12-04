package com.trov.fred;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.trov.fred.adapter.BeaconListAdapter;
import com.trov.fred.beacon.LlunyManager;
import com.trov.fred.beacon.LlunyManagerImpl;
import com.trov.fred.view.CustomDialog;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity
        implements BeaconListAdapter.OnItemClickListener, LlunyManager.Callback {

    DrawerLayout drawerLayout;
    ViewGroup content;
    RecyclerView mBeaconList;

    LlunyManager mLlunyManager = new LlunyManagerImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFab();
        initToolbar();
        setupDrawerLayout();

        content = (ViewGroup) findViewById(R.id.content);
        mBeaconList = (RecyclerView) findViewById(R.id.recycler);
        mBeaconList.setLayoutManager(new LinearLayoutManager(this));

        mLlunyManager.start(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLlunyManager.stop();
    }

    private void initFab() {
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLlunyManager();
                //Snackbar.make(content, "FAB Clicked", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void startLlunyManager() {
        mLlunyManager.start(this);
    }

    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView view = (NavigationView) findViewById(R.id.navigation_view);
        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Snackbar.make(content, menuItem.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, Beacon item) {
        //DetailActivity.navigate(this, view.findViewById(R.id.image), viewModel);
        CustomDialog customDialog = new CustomDialog(this);
        customDialog.setMessage("Deseas selecionar este LLunY? " + item.getId1().toString());
        customDialog.add(android.R.id.content);
    }

    @Override
    public void onBeaconsDetected(Collection<Beacon> beacons, Region region) {
        //mLlunyManager.stop();
        BeaconListAdapter adapter = new BeaconListAdapter(new ArrayList<>(beacons));
        adapter.setOnItemClickListener(this);
        mBeaconList.setAdapter(adapter);
    }
}
