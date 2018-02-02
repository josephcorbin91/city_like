package com.android.jco.citylike_android.applications;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by jco on 2/2/2018.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
            System.out.println("In base application");
            FirebaseApp.initializeApp(getApplicationContext());
        }

}
