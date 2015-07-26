package com.shareqube.androidjokesLib;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class JokesActivity extends AppCompatActivity {

    public static String JOKE_EXTRA = "JOKE_EXTRA" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);

       Intent intent  =getIntent();
        if(intent != null) {
            String aJoke = intent.getStringExtra(JOKE_EXTRA);
            TextView jokeTextView = (TextView) findViewById(R.id.joke_id);

            jokeTextView.setText(aJoke);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_jokes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public  static Intent jokeIntent(Context c , String joke){
        Intent jokeIntent = new Intent(c  , JokesActivity.class);
        jokeIntent.putExtra(JOKE_EXTRA , joke);


        return jokeIntent ;
    }
}
