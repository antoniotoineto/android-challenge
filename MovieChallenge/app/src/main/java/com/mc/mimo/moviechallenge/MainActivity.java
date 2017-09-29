package com.mc.mimo.moviechallenge;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.mc.mimo.moviechallenge.view.list.MovieListFragment;
import com.mc.mimo.moviechallenge.view.search.MovieSearchFragment;

public class MainActivity extends AppCompatActivity {

    final FragmentManager fragmentManager = getSupportFragmentManager();
    final MovieListFragment movieListFragment = new MovieListFragment();
    final MovieSearchFragment movieSearchFragment = new MovieSearchFragment();
    private int currentFragment = 1;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setTitle(R.string.title_home);
                    changToFragment(1);
                    movieListFragment.changeMovieListDataSet(1);
                    return true;
                case R.id.navigation_popular:
                    setTitle(R.string.title_popular);
                    changToFragment(1);
                    movieListFragment.changeMovieListDataSet(2);
                    return true;
                case R.id.navigation_toprated:
                    setTitle(R.string.title_toprated);
                    changToFragment(1);
                    movieListFragment.changeMovieListDataSet(3);
                    return true;
                case R.id.navigation_search:
                    setTitle(R.string.title_search);
                    changToFragment(2);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content, movieListFragment).commit();

        setTitle(R.string.title_home);
    }

    private void changToFragment(int newFragment) {
        if (currentFragment != newFragment) {
            currentFragment = newFragment;
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            switch (newFragment) {
                case 1: fragmentTransaction.replace(R.id.content, movieListFragment).commit(); break;
                case 2: fragmentTransaction.replace(R.id.content, movieSearchFragment).commit(); break;
            }
        }
    }
}
