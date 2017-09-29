package com.mc.mimo.moviechallenge.view.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mc.mimo.moviechallenge.R;
import com.mc.mimo.moviechallenge.api.APIClient;
import com.mc.mimo.moviechallenge.api.APIInterface;
import com.mc.mimo.moviechallenge.pojo.moviedetails.Movie;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {

    private int movie_id;
    private APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        setTitle(R.string.title_details);

        movie_id = getIntent().getExtras().getInt("movie_id");

        apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<Movie> call = apiInterface.doGetMovieDetails(movie_id, "pt-BR");

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                showMovieInfo(response.body());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                call.cancel();
            }
        });
    }

    private void showMovieInfo(Movie movie) {
        ImageView posterImage = (ImageView) findViewById(R.id.posterImg);
        TextView title = (TextView) findViewById(R.id.title);
        TextView movieOverview = (TextView) findViewById(R.id.movieOverview);



        title.setText(movie.title);
        movieOverview.setText(movie.overview);
        Picasso.with(posterImage.getContext()).load(APIClient.IMAGEBASEURL + movie.posterPath).into(posterImage);

        findViewById(R.id.loadingPanel).setVisibility(View.GONE);
        findViewById(R.id.movieInfoPanel).setVisibility(View.VISIBLE);
    }
}
