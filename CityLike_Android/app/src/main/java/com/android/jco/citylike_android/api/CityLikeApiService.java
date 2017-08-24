
package com.android.jco.citylike_android.api;


import android.content.Context;


import com.android.jco.citylike_android.models.SeattleBuildingPermit;

import org.litepal.crud.DataSupport;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class CityLikeApiService {

    public static final String BASE_URL = "https://citylike1.herokuapp.com/";
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
        Call<List<SeattleBuildingPermit>> getSpecificSeattleBuildingPermit(@Path("permit_number") Integer permit_number);

        @GET("buildingPermits")
        Call<List<SeattleBuildingPermit>> getAllSeattleBuilingPermitS();


    }
    public void getSeattleBuilingPermit(Context context, Integer permit_number) throws Exception {
        System.out.println("Permit number" + permit_number);

        Call<List<SeattleBuildingPermit>> getSpecificSeattleBuildingPermitCall = apiService.getSpecificSeattleBuildingPermit(permit_number);

        getSpecificSeattleBuildingPermitCall.enqueue(new Callback<List<SeattleBuildingPermit>>() {
            @Override
            public void onResponse(Call<List<SeattleBuildingPermit>> call, Response<List<SeattleBuildingPermit>> response) {
                System.out.println("RESPONSE SUCCESS "+ response.body().get(0).getLatitude().toString());

            }

            @Override
            public void onFailure(Call<List<SeattleBuildingPermit>> call, Throwable t) {
                System.out.println("RESPONSE ERROR " + t.toString());

            }
        });

    }
    public void getAllSeattleBuilingPermit(Context context) throws Exception {

        Call<List<SeattleBuildingPermit>> getAllSeattleBuildingPermitCall = apiService.getAllSeattleBuilingPermitS();

        getAllSeattleBuildingPermitCall.enqueue(new Callback<List<SeattleBuildingPermit>>() {
            @Override
            public void onResponse(Call<List<SeattleBuildingPermit>> call, Response<List<SeattleBuildingPermit>> response) {
                System.out.println("RESPONSE SUCCESS ");
                DataSupport.deleteAll(SeattleBuildingPermit.class);

                for(SeattleBuildingPermit permit : response.body()) {
                    System.out.println(permit);

                        SeattleBuildingPermit seattleBuildingPermit = new SeattleBuildingPermit(permit.getPermitNumber(), permit.getCategory(), permit.getDescription(), permit.getDate(), permit.getAddress(), permit.getValue(), permit.getLatitude(), permit.getLongitude());
                        seattleBuildingPermit.saveThrows();

                }



            }

            @Override
            public void onFailure(Call<List<SeattleBuildingPermit>> call, Throwable t) {
                System.out.println("RESPONSE ERROR " + t.toString());

            }
        });

    }
}




