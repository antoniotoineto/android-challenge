package com.mc.mimo.moviechallenge.view.list;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mc.mimo.moviechallenge.R;
import com.mc.mimo.moviechallenge.api.APIClient;
import com.mc.mimo.moviechallenge.pojo.movielist.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.mc.mimo.moviechallenge.R.id.imageView;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private final List<Result> mValues;

    public MovieListAdapter(List<Result> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_nowplaying, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Result result = mValues.get(position);
        if (result.posterPath != null) {
            Picasso.with(holder.mImageView.getContext()).load(APIClient.IMAGEBASEURL + result.posterPath).into(holder.mImageView);
        }

        holder.mMovieTitleView.setText(mValues.get(position).title);
        holder.mOverviewView.setText(mValues.get(position).overview);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Exibir mais detalhes do filme
                Intent i = new Intent(v.getContext(), MovieDetailsActivity.class);
                i.putExtra("movie_id", result.id);
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImageView;
        public final TextView mMovieTitleView;
        public final TextView mOverviewView;

        public Result mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = view.findViewById(imageView);
            mMovieTitleView = view.findViewById(R.id.movieTitle);
            mOverviewView = view.findViewById(R.id.movieOverview);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mMovieTitleView.getText() + "'";
        }
    }
}
