package com.example.arnal.popularmovieapp2;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.arnal.popularmovieapp2.adapter.MovieAdapter;
import com.example.arnal.popularmovieapp2.model.MessageEvent;
import com.example.arnal.popularmovieapp2.model.Movie;
import com.example.arnal.popularmovieapp2.model.MovieResult;
import com.example.arnal.popularmovieapp2.model.MoviesAPI;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment {
    static List<Movie> movieList;
    private MovieAdapter adapter;


    public PopularFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       // getActivity().getActionBar().setTitle(R.string.popular);

        getPopularMovies();

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_popular, container,false);
        adapter = new MovieAdapter(getActivity(), movieList);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        return recyclerView;
    }

    public void getPopularMovies() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final MoviesAPI movies = retrofit.create(MoviesAPI.class);
        movieList = new ArrayList<>();
        movies.getPopularMovie(MainActivity.API_KEY).enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                MovieResult result = response.body();
                movieList = result.getResults();
                adapter.setMovieList(movieList);

                EventBus.getDefault().post(new MessageEvent(0, "PopularFragment", 0));
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {

                Toast toast = Toast.makeText(getActivity(), R.string.network_unavailable, Toast.LENGTH_SHORT);
                toast.show();

            }
        });
    }
}
