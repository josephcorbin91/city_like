package com.android.jco.citylike_android.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.android.jco.citylike_android.R;
import com.android.jco.citylike_android.api.CityLikeApiService;


import org.litepal.LitePal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {



    @BindView(R.id.email_login_button)
    Button email_login_button;


    private VideoView login_activity_video;
    private MediaController mediaControls;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);


    }

    @OnClick(R.id.email_login_button)
    public void signUpWithEmail(){


    }
    @OnClick(R.id.facebook_login_button)
    public void signUpWithFacebook(){

    }
    @OnClick(R.id.google_login_button)
    public void signUpWithGoogle(){

    }
}
