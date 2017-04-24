package com.willowtree.namegame.view;

import com.willowtree.namegame.controller.ActivityController;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.willowtree.namegame.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.container)
    FrameLayout frameLayout;

    private static final String TAG_NORMAL = "NormalFragment";
    private NormalFragment normalFragment;

    private static final String TAG_REVERSE = "ReverseFragment";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        new ActivityController(this);
//        if (savedInstanceState == null) {
//            new ActivityController(this);
//        } else {
//            hideLoader();
//            normalFragment = (NormalFragment) getSupportFragmentManager().findFragmentByTag(TAG_NORMAL);
//            if (normalFragment == null) {
//                savedInstanceState.putInt("GameMode", 0);
//                NormalFragment normalFragment = new NormalFragment();
//                normalFragment.setArguments(savedInstanceState);
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.container, normalFragment, TAG_NORMAL)
//                        .setTransition(FragmentTransaction.TRANSIT_ENTER_MASK)
//                        .commit();
//            } else {
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.container, normalFragment, TAG_NORMAL)
//                        .setTransition(FragmentTransaction.TRANSIT_ENTER_MASK)
//                        .commit();
//            }
//        }

        //loadInitFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Bundle bundle = new Bundle();
        switch (id) {
            case R.id.normal_mode :
                normalFragment = (NormalFragment) getSupportFragmentManager().findFragmentByTag(TAG_NORMAL);
                if (normalFragment == null) {
                    bundle.putInt("GameMode", 0);
                    NormalFragment normalFragment = new NormalFragment();
                    normalFragment.setArguments(bundle);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, normalFragment, TAG_NORMAL)
                            .setTransition(FragmentTransaction.TRANSIT_ENTER_MASK)
                            .commit();
                }
                break;
            case R.id.matt_mode :
                bundle.putInt("GameMode", 1);
                NormalFragment mattFragment = new NormalFragment();
                mattFragment.setArguments(bundle);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, mattFragment)
                        .setTransition(FragmentTransaction.TRANSIT_ENTER_MASK)
                        .commit();
                break;
            case R.id.reverse_mode :
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new ReverseFragment())
                        .setTransition(FragmentTransaction.TRANSIT_ENTER_MASK)
                        .commit();
                break;
            case R.id.hint_mode :
                bundle.putInt("GameMode", 2);
                NormalFragment hintFragment = new NormalFragment();
                hintFragment.setArguments(bundle);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, hintFragment)
                        .setTransition(FragmentTransaction.TRANSIT_ENTER_MASK)
                        .commit();
                break;
            case R.id.report :
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new ReportFragment())
                        .setTransition(FragmentTransaction.TRANSIT_ENTER_MASK)
                        .commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void hideLoader() {
        progressBar.setVisibility(View.GONE);
        frameLayout.setVisibility(View.VISIBLE);
    }

    private void loadInitFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new NormalFragment())
                .setTransition(FragmentTransaction.TRANSIT_ENTER_MASK)
                .commit();
    }
}
