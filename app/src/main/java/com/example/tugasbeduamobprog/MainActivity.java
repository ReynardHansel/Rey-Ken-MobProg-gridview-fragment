package com.example.tugasbeduamobprog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FrameLayout container;
    HomeFragment homeFragment = new HomeFragment();
    MyFragment myFragment = new MyFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        container = findViewById(R.id.container);

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
                } else if (itemId == R.id.myfragment) {
                    replaceFragmentWithTransition(myFragment);
                }
                return true;
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
}
