package com.mc.mimo.moviechallenge.view.search;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mc.mimo.moviechallenge.R;
import com.mc.mimo.moviechallenge.api.APIClient;
import com.mc.mimo.moviechallenge.api.APIInterface;
import com.mc.mimo.moviechallenge.pojo.search.Result;
import com.mc.mimo.moviechallenge.pojo.search.Search;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieSearchFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    private List<Result> results = new ArrayList<>();
    private MovieSearchAdapter adapter = new MovieSearchAdapter(results);

    private APIInterface apiInterface;

    private RecyclerView listView;
    private View loadingPanel;
    private EditText inputSearch;

    public MovieSearchFragment() {
    }


    public static MovieSearchFragment newInstance(int columnCount) {
        MovieSearchFragment fragment = new MovieSearchFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_moviesearch_list, container, false);
        this.listView = view.findViewById(R.id.listSearch);

        if (this.listView instanceof RecyclerView) {
            Context context = view.getContext();
            if (mColumnCount <= 1) {
                this.listView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                this.listView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            this.listView.setAdapter(adapter);
        }



        this.listView = view.findViewById(R.id.listSearch);
        this.loadingPanel = view.findViewById(R.id.loadingPanelSearch);
        this.inputSearch = view.findViewById(R.id.inputSearch);

        view.findViewById(R.id.buttonSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Tratar queries inválidas
                String query = inputSearch.getText().toString();
                showLoading();
                search(query);
            }
        });

        return view;
    }

    private void search(String query) {
        Call<Search> call = apiInterface.doSearch(query);

        call.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                results.clear();
                results.addAll(response.body().results);
                adapter.notifyDataSetChanged();
                showList();
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                call.cancel();
            }
        });
    }

    private void showLoading() {
        this.loadingPanel.setVisibility(View.VISIBLE);
        this.listView.setVisibility(View.GONE);
    }

    private void showList() {
        this.loadingPanel.setVisibility(View.GONE);
        this.listView.setVisibility(View.VISIBLE);
    }
}
