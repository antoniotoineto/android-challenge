package com.mc.mimo.moviechallenge;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.mc.mimo.moviechallenge.nowplaying.NowPlayingFragment;
import com.mc.mimo.moviechallenge.nowplaying.dummy.DummyContent;
import com.mc.mimo.moviechallenge.pojo.NowPlaying;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NowPlayingFragment.OnListFragmentInteractionListener {

    private TextView mTextMessage;

    final FragmentManager fragmentManager = getSupportFragmentManager();
    final NowPlayingFragment nowPlayingFragment = new NowPlayingFragment();
    APIInterface apiInterface;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content, nowPlayingFragment).commit();

                    return true;
                case R.id.navigation_dashboard:
                    //
                    return true;
                case R.id.navigation_notifications:
                    //
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


        apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<NowPlaying> call2 = apiInterface.doGetNowPlaingList("1", "pt-BR");
        call2.enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                response.code();
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {
                call.cancel();
            }
        });
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
