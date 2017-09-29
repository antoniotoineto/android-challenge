package com.mc.mimo.moviechallenge.view.list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mc.mimo.moviechallenge.api.APIClient;
import com.mc.mimo.moviechallenge.api.APIInterface;
import com.mc.mimo.moviechallenge.R;
import com.mc.mimo.moviechallenge.pojo.movielist.MovieList;
import com.mc.mimo.moviechallenge.pojo.movielist.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private int listType = 1;

    private List<Result> results = new ArrayList<>();
    private MovieListAdapter adapter = new MovieListAdapter(results);


    private APIInterface apiInterface;

    public MovieListFragment() {
    }

    public static MovieListFragment newInstance(int columnCount) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        changeMovieListDataSet(this.listType);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nowplaying_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            recyclerView.setAdapter(adapter);
        }
        return view;
    }

    public void changeMovieListDataSet(int listType) {
        Call<MovieList> call = null;

        switch (listType) {
            case 1: call = apiInterface.doGetNowPlaingList("1", "pt-BR"); break;
            case 2: call = apiInterface.doGetPopularList("1", "pt-BR"); break;
            case 3: call = apiInterface.doGetTopRatedList("1", "pt-BR"); break;
        }

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                results.clear();
                results.addAll(response.body().results);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                call.cancel();
            }
        });

        this.listType = listType;
    }
}
