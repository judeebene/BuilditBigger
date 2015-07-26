package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.text.TextUtils;

/**
 * Created by Jude Ben on 7/23/2015.
 */
public class JokesTest  extends AndroidTestCase {

    public void  asyntakJokeTest(){
        EndpointsAsyncTask  endpointsAsyncTask = new EndpointsAsyncTask(getContext());

        endpointsAsyncTask.downloadFunnyJokes();
        endpointsAsyncTask.setAsyncJokeDownloadListener(new EndpointsAsyncTask.AsyncJokeDownloadListener() {
            @Override
            public void onAsyncJokeDownloaded(String joke) {
                assertEquals(true, !TextUtils.isEmpty(joke));
            }
        });

    }
}
