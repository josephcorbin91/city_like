
package com.android.jco.citylike_android.api;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;


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
import retrofit2.http.Path;
import retrofit2.http.Query;

public class CityLikeApiService {

    public static final String BASE_URL = "http://192.168.0.33";
    CityLikeApiEndpointInterface apiService;

    private Context mContext = null;

    public CityLikeApiService() {



        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        apiService = retrofit.create(CityLikeApiEndpointInterface.class);

}



public interface CityLikeApiEndpointInterface {

        /*TODO add authentication token */
        //SkyscraperCityPosts
        @GET("buildingPermits/{permit_number}")
        Call<SeattleBuildingPermit> getSeattleBuilingPermit(@Path("permit_number") Integer permit_number);


    }

    public void getSeattleBuilingPermit(Context context, Integer permit_number) throws Exception {
        Call<SeattleBuildingPermit> call = apiService.getSeattleBuilingPermit(permit_number);

        System.out.println("Permit number" + permit_number);
        Response<SeattleBuildingPermit> response = call.execute();
        Integer statusCode = response.code();
        if (statusCode == HttpURLConnection.HTTP_OK || statusCode == HttpURLConnection.HTTP_CREATED) {
            Toast.makeText(context, response.body().getPermitNumber(), Toast.LENGTH_SHORT).show();
        } else {
            throw new RuntimeException("ERROR createKey: " + response.errorBody().string() + " : " + statusCode.toString());
        }
    }
}
