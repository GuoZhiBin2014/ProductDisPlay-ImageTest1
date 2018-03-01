package com.cambricon.productdisplay.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.cambricon.productdisplay.R;
import com.cambricon.productdisplay.db.CommDB;

/**
 * Created by dell on 18-2-3.
 */

public class HomeActivity extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    /*创建一个Drawerlayout和Toolbar联动的开关*/
    private ActionBarDrawerToggle toggle;

    private DrawerLayout drawerLayout;
    private RadioGroup radioGroup;
    private LinearLayout relMenu;

    private TestFragment testFragment;
    private DataFragment dataFragment;
    private NewsFragment newsFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    final int PERMISSION_REQUST_CODE = 0x001;

    private CommDB commDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        initView();
        initFragment();
        setActionBar();
        setListener();
        setDrawerToggle();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUST_CODE);
        } else {

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUST_CODE) {

        }
    }

    private void setDrawerToggle() {
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(toggle);
        /*同步drawerlayout的状态*/
        toggle.syncState();
    }

    private void initView() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.mydrawer);
        radioGroup = findViewById(R.id.rel_navigate);
        commDB=new CommDB(this);
        commDB.open();

    }

    private void initFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        testFragment = new TestFragment();
        fragmentTransaction.add(R.id.main_content, testFragment);
        fragmentTransaction.commit();
    }

    private void hideAll(FragmentTransaction transaction) {
        if (transaction == null) {
            return;
        }
        if (testFragment != null) {
            transaction.hide(testFragment);
        }

        if (dataFragment != null) {
            transaction.hide(dataFragment);
        }

        if (newsFragment != null) {
            transaction.hide(newsFragment);
        }
    }

    private void setListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                Log.d("huangyaling", "checkId:" + checkId);
                switch (checkId) {
                    case R.id.tab_test:

                        FragmentTransaction testTransaction = fragmentManager.beginTransaction();
                        hideAll(testTransaction);
                        if (testFragment == null) {
                            testFragment = new TestFragment();
                            testTransaction.add(R.id.main_content, testFragment);
                        } else {
                            testTransaction.show(testFragment);
                        }
                        testTransaction.commit();
                        break;
                    case R.id.tab_data:
                        FragmentTransaction dataTransaction = fragmentManager.beginTransaction();
                        hideAll(dataTransaction);
                        if (dataFragment == null) {
                            dataFragment = new DataFragment();
                            dataTransaction.add(R.id.main_content, dataFragment);
                        } else {
                            dataTransaction.show(dataFragment);
                        }
                        dataTransaction.commit();
                        break;
                    case R.id.tab_adv:
                        FragmentTransaction newsTransaction = fragmentManager.beginTransaction();
                        hideAll(newsTransaction);
                        if (newsFragment == null) {
                            newsFragment = new NewsFragment();
                            newsTransaction.add(R.id.main_content, newsFragment);
                        } else {
                            newsTransaction.show(newsFragment);
                        }
                        newsTransaction.commit();
                        break;
                }
            }
        });
    }

    /**
     * 设置ActionBar
     */
    private void setActionBar() {
        setSupportActionBar(toolbar);
        /*显示Home图标*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onDestroy() {
        if(commDB!=null){
            commDB.close();
        }
        super.onDestroy();
    }
}
