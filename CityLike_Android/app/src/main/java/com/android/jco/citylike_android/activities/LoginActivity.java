package com.android.jco.citylike_android.activities;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.android.jco.citylike_android.R;

public class LoginActivity extends AppCompatActivity {

    private VideoView login_activity_video;
    private MediaController mediaControls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mediaControls = new MediaController(LoginActivity.this);
        login_activity_video = (VideoView) findViewById(R.id.login_activity_video);
        try{
            login_activity_video.setMediaController(mediaControls);
            login_activity_video.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.seattle_skyline_video));
        }
        catch (Exception exception){
            Log.e("Error", exception.getMessage());
            exception.printStackTrace();
        }
        login_activity_video.requestFocus();
        login_activity_video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                login_activity_video.start();
            }
        });


    }

}
