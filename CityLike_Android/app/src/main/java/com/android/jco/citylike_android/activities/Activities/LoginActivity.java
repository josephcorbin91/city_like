package com.android.jco.citylike_android.activities.Activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import butterknife.BindView;
import butterknife.OnClick;

import com.android.jco.citylike_android.R;

public class LoginActivity extends AppCompatActivity {



    @BindView(R.id.login_activity_button_sign_in)
    Button login_activity_button_sign_in;


    private VideoView login_activity_video;
    private MediaController mediaControls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        login_activity_button_sign_in = (Button)findViewById(R.id.login_activity_button_sign_in);
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


    @OnClick(R.id.login_activity_button_sign_in)
    public void transitionToSwipingActivity(){
        Intent intent = new Intent(this,SwipingActivity.class);
        startActivity(intent);
    }

}
