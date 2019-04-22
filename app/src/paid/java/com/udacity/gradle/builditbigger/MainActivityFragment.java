package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.jokepresenter.JokeActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.DataReceivedCallback {

    public static final String JOKE_TAG = "joke";
    private ProgressBar pbLoad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        pbLoad = root.findViewById(R.id.pbLoad);

        root.findViewById(R.id.btnTellJoke).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbLoad.setVisibility(View.VISIBLE);
                requestJoke();
            }
        });

        return root;
    }

    public void requestJoke() {
        new EndpointsAsyncTask(this).execute();
    }

    @Override
    public void onDataReceived(String data) {
        Intent intent = new Intent(getActivity(), JokeActivity.class);
        intent.putExtra(JOKE_TAG, data);
        startActivity(intent);
        pbLoad.setVisibility(View.GONE);
    }
}
