package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.shareqube.android.backend.myApi.MyApi;


import java.io.IOException;

/**
 * Created by Jude Ben on 7/23/2015.
 */
public class EndpointsAsyncTask {

    AsyncJokeDownloadListener asyncJokeDownloadListener ;

    Context c;
    boolean isDialogVisible  = false;
    public EndpointsAsyncTask(Context context){

        this.c = context ;

    }

    public void downloadFunnyJokes(){
        AsyncTackJokes asyncTackJokes = new AsyncTackJokes();
        asyncTackJokes.execute();


    }

    public  void setDialogVisibility(Boolean isDialogVisible){

        this.isDialogVisible  = isDialogVisible ;
    }

    public class  AsyncTackJokes extends AsyncTask<Void,Void, String>{

        public  MyApi apiService = null ;
        ProgressDialog mProgressDialog ;

        public AsyncTackJokes(){

            mProgressDialog = new ProgressDialog(c);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if(isDialogVisible){
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mProgressDialog.setMessage(c.getString(R.string.loading));
                mProgressDialog.show();
            }
        }

        @Override
        protected String doInBackground(Void... params) {

            if(apiService == null){

            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport()
                    , new AndroidJsonFactory(), null)
                    .setRootUrl("https://buildbigger-udacity.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {

                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            apiService = builder.build();
        }

        try{
            return apiService.jokes().execute().getData();
        }catch(IOException e) {
            return e.getMessage();

        }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(isDialogVisible){
                mProgressDialog.dismiss();
            }
            asyncJokeDownloadListener.onAsyncJokeDownloaded(s);
        }
    }

    public void  setAsyncJokeDownloadListener(AsyncJokeDownloadListener listener){
        this.asyncJokeDownloadListener = listener;
    }
    public interface AsyncJokeDownloadListener {
        void onAsyncJokeDownloaded(String joke);
    }
}
