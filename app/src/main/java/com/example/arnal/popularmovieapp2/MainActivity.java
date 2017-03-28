package com.example.arnal.popularmovieapp2;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;

import com.facebook.stetho.Stetho;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends Activity {

    static final String ENDPOINT = "http://api.themoviedb.org/3/";
    static final String API_KEY = BuildConfig.MOVIES_TMDB_API_KEY;
    private Fragment popularFragment;
    private String title;
    static boolean isDualPane;

    @Nullable
    @BindView(R.id.right_container)
    View detailContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);

        ButterKnife.bind(this);

        if(detailContainer != null && detailContainer.getVisibility() == View.VISIBLE){
            isDualPane = true;
        }

        if(savedInstanceState != null){
            title = savedInstanceState.getString("title");
            getActionBar().setTitle(title);
        }else {
            popularFragment = new PopularFragment();
            generateTransaction(popularFragment);

        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("title", title);
    }


    private void generateTransaction(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.left_container, fragment, "visible_fragment");
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
