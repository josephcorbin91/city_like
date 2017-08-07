
package com.android.jco.citylike_android.api;


import android.content.Context;
import android.util.Log;


import com.android.jco.citylike_android.models.SeattleBuildingPermit;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.zip.GZIPInputStream;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public class CityLikeApiService {

    public static final String BASE_URL = "http://citylike.herokuapp.com/";
    public static final String ENV_SERVEUR = "citylike-production";
    CityLikeApiEndpointInterface apiService;

    private Context mContext = null;

    public CityLikeApiService() {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService =
                retrofit.create(CityLikeApiEndpointInterface.class);

    }



    public interface CityLikeApiEndpointInterface {

        /*TODO add authentication token */
        //SkyscraperCityPosts
        @GET("seattleBuildingPermit/posts")
        Call<SeattleBuildingPermit> getSeattleBuilingPermits();
    }

    public void getSeattleBuilingPermits(Context context) throws Exception {
        mContext = context;
        Call<SeattleBuildingPermit> call = apiService.getSeattleBuilingPermits();

        Response<SeattleBuildingPermit> response = call.execute();
        Integer statusCode = response.code();
        if (statusCode == HttpURLConnection.HTTP_OK || statusCode == HttpURLConnection.HTTP_CREATED) {
        } else {
            throw new RuntimeException("ERROR createKey: " + response.errorBody().string() + " : " + statusCode.toString());
        }
    }
}
