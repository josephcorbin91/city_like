package com.android.jco.citylike_android.activities;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.media.MediaRouteProvider;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import butterknife.BindView;
import butterknife.OnClick;


import com.android.jco.citylike_android.R;
import com.android.jco.citylike_android.api.CityLikeApiService;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnSuccessListener;

import org.litepal.LitePal;

public class LoginActivity extends AppCompatActivity {



    @BindView(R.id.login_activity_button_sign_in)
    Button login_activity_button_sign_in;


    private VideoView login_activity_video;
    private MediaController mediaControls;
    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LitePal.initialize(this);

        try {
            CityLikeApiService apiService = new CityLikeApiService();
            apiService.getAllSeattleBuilingPermit(getApplicationContext());
        }
        catch(Exception ex){

        }


        callbackManager = CallbackManager.Factory.create();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        login_activity_button_sign_in = (Button)findViewById(R.id.login_activity_button_sign_in);
        FloatingActionButton swipeButton = (FloatingActionButton) findViewById(R.id.swipe);
        swipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SwipingActivity.class));
            }
        });
        loginButton = (LoginButton)findViewById(R.id.facebook_login_button);


        loginButton.setReadPermissions("email");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        mediaControls = new MediaController(LoginActivity.this);
        login_activity_video = (VideoView) findViewById(R.id.login_activity_video);
        try{
            login_activity_video.setMediaController(mediaControls);
            login_activity_video.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.seattle_skyline));


        }
        catch (Exception exception){
            Log.e("Error", exception.getMessage());
            exception.printStackTrace();
        }
        login_activity_video.requestFocus();
        login_activity_video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
                login_activity_video.setMediaController(null);

                login_activity_video.start();


            }
        });


    }


    @OnClick(R.id.login_activity_button_sign_in)
    public void transitionToSwipingActivity(){
        Intent intent = new Intent(this,SwipingActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }

}
