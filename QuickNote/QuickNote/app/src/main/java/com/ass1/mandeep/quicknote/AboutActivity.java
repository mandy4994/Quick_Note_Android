package com.ass1.mandeep.quicknote;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Mandeep Singh, 213347007
 * SIT207 Assignment 2
 */
public class AboutActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_about);
        ImageButton fbButton = (ImageButton) findViewById(R.id.btnFb);
        ImageButton mapButton = (ImageButton) findViewById(R.id.btnMap);

        //OnClick listener for facebook intent
        fbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/mandy4994"));
                startActivity(intent);
            }
        });

        //OnClick listener for map intent
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:-37.847930, 145.114495"));
                startActivity(intent);
            }
        });
        VideoView myVideoView = (VideoView) findViewById(R.id.videoView);
        String srcPath = "android.resource://" + getPackageName() + "/" + R.raw.video;
        myVideoView.setVideoURI(Uri.parse(srcPath));
        myVideoView.setMediaController(new MediaController(this));
        myVideoView.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about, menu);
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

}
