package com.example.tugasbeduamobprog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MyFragment.OnDataTransferListener {

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment;
    MyFragment myFragment;
    SharedViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        homeFragment = new HomeFragment();
        myFragment = new MyFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.myfragment);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(8);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                } else if (itemId == R.id.myfragment) {
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
