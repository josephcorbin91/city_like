package com.android.jco.citylike_android.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.android.jco.citylike_android.api.CityLikeApiService;

import org.litepal.crud.DataSupport;

import java.util.Iterator;
import java.util.List;

/**
 * Created by JCO on 8/19/2017.
 */

public class ApiIntentService extends IntentService {
        public ApiIntentService() {
            super("SynchroniseService");
        }

        @Override
        protected void onHandleIntent(Intent workIntent) {
            try {
                CityLikeApiService apiService = new CityLikeApiService();
                apiService.getSeattleBuilingPermit(getApplicationContext(), 6279866);
            }
            catch (Exception ex){

            }

    }

}
