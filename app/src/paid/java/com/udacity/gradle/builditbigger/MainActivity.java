package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;


import com.jokes.JokesManager;
import com.shareqube.androidjokesLib.JokesActivity;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.R;

/**
 * Created by Jude Ben on 7/21/2015.
 */
public class MainActivity extends AppCompatActivity  implements EndpointsAsyncTask.AsyncJokeDownloadListener{

    //  paid version no ads
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void tellJoke(View view){

        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(this);



        endpointsAsyncTask.setDialogVisibility(true);

        endpointsAsyncTask.setAsyncJokeDownloadListener(this);

        endpointsAsyncTask.downloadFunnyJokes();


    }




    @Override
    public void onAsyncJokeDownloaded(final String joke) {

        if(isNetworkAvailable(this)){

               getJokes(joke);

        }else {
            Toast.makeText(this , getString(R.string.no_internet),Toast.LENGTH_LONG).show();
        }
    }

    public void getJokes(String joke){
        if(!TextUtils.isEmpty(joke)) {
            final Intent jokeIntent = JokesActivity.jokeIntent(this, joke);

            startActivity(jokeIntent);
        }else {
            Toast.makeText(this, getString(R.string.no_joke_serice), Toast.LENGTH_LONG).show();
        }

    }

    public static Boolean isNetworkAvailable(Context c) {

        ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();

    }
}
