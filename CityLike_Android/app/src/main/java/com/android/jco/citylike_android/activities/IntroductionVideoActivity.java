package com.android.jco.citylike_android.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


import com.android.jco.citylike_android.R;
import com.android.jco.citylike_android.api.CityLikeApiService;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.widget.LoginButton;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import org.litepal.LitePal;

public class IntroductionVideoActivity extends AppCompatActivity {


    private static final int RC_SIGN_IN = 123;

    @BindView(R.id.login_activity_button_sign_in)
    Button login_activity_button_sign_in;


    private VideoView login_activity_video;
    private MediaController mediaControls;
    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction_video_activity);
        LitePal.initialize(this);
        ButterKnife.bind(this);
        try {
            CityLikeApiService apiService = new CityLikeApiService();
            apiService.getAllSeattleBuilingPermit(getApplicationContext());
        }
        catch(Exception ex){

        }


        callbackManager = CallbackManager.Factory.create();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);



        mediaControls = new MediaController(IntroductionVideoActivity.this);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }


    @OnClick(R.id.login_activity_button_sign_in)
    public void transitionToSwipingActivity(){

        System.out.println("clicking button");
        Toast.makeText(this, "Trying to sign in ", Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(this,SignUpActivity.class);
        //startActivity(intent);
        //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            // already signed in
        } else {
            startActivityForResult(
                    // Get an instance of AuthUI based on the default app
                    AuthUI.getInstance().createSignInIntentBuilder().build(),
                    RC_SIGN_IN);        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == RESULT_OK) {
                //startActivity(new Intent(getApplicationContext(),SwipingActivity.class ));
                finish();
                return;
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    Toast.makeText(this, "Sign in cancelled", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    Toast.makeText(this, "Unkown error", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

        }
    }
}
