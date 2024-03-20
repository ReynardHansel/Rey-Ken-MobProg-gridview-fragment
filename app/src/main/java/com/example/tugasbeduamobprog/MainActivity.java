package com.example.tugasbeduamobprog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MyFragment.OnDataTransferListener {

    BottomNavigationView bottomNavigationView;
    FrameLayout container;
    HomeFragment homeFragment = new HomeFragment();
    MyFragment myFragment = new MyFragment();
    SharedViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        container = findViewById(R.id.container);
        homeFragment = new HomeFragment();
        myFragment = new MyFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.myfragment);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(8);

        replaceFragmentWithTransition(homeFragment);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.home) {
                    replaceFragmentWithTransition(homeFragment);
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                } else if (itemId == R.id.myfragment) {
                    replaceFragmentWithTransition(myFragment);
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).commit();
                }
                return true;
            }
        });
        viewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        viewModel.getData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                myFragment.updateData(strings);
            }
        });
    }

    // Inside transitionBackground() method
    private void transitionBackground(int startResId, int endResId) {
        Drawable startDrawable = ContextCompat.getDrawable(this, startResId);
        Drawable endDrawable = ContextCompat.getDrawable(this, endResId);

        TransitionDrawable transition = new TransitionDrawable(new Drawable[]{startDrawable, endDrawable});

        container.setBackground(transition);
        transition.startTransition(500); // Transition duration in milliseconds
    }

    // Method to replace fragments with background transition
    public void replaceFragmentWithTransition(Fragment fragment) {
        int startResId = 0;
        int endResId = 0;

        if (fragment instanceof HomeFragment) {
            startResId = R.drawable.background_home;
            endResId = R.drawable.background_my;
        } else if (fragment instanceof MyFragment) {
            startResId = R.drawable.background_my;
            endResId = R.drawable.background_home;
        } else {
            return;
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();

        transitionBackground(startResId, endResId);
    }

    @Override
    public void onDataTransfer(String data) {
        Log.d("MainActivity", "Data received from HomeFragment: " + data);
        if (myFragment.isAdded()) {
            Log.d("MainActivity", "Passing data to MyFragment");
            myFragment.addData(data);
        }
        Log.d("MainActivity", "Passing data to MyFragment (without bypassing if)");
        myFragment.addData(data);
    }
}
