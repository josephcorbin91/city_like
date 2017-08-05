
package com.android.jco.citylike_android.api;


import android.content.Context;
import android.util.Log;



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
    /*
    public static final String BASE_URL = "http://mykeyring.herokuapp.com/api/v1/";
    //public static final String BASE_URL = "http://aec310c6.ngrok.io/api/v1/";
    public static final String ENV_SERVEUR = "mykeys-production";
    MyKeysApiEndpointInterface apiService;

    private Context mContext = null;
    private User mUser = null;
    private Key mKey = null;
    private Keyring mKeyring = null;
    private Lock mLock = null;

    public CityLikeApiService() {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService =
                retrofit.create(MyKeysApiEndpointInterface.class);

    }



    public interface MyKeysApiEndpointInterface {

        //Users
        @DELETE("users/delete_test_user")
        Call<User> deleteTestUser();

        @GET("users/login")
        Call<User> loginUser(@Query("email") String email, @Query("password") String password);

        @POST("users/create")
        Call<User> createUser(@Body User user);

        @PUT("users/update")
        Call<User> updateUser(@Body User user);



        //Keyrings
        @POST("keyrings/create")
        Call<Keyring> createKeyring(@Query("authentification_token") String authentification_token,
                                    @Body Keyring keyring);
        @PUT("keyrings/update")
        Call<Keyring> updateKeyring(@Query("authentification_token") String authentification_token,
                                    @Body Keyring keyring);

        @DELETE("keyrings/delete/")
        Call<Keyring> deleteKeyring(@Query("authentification_token") String authentification_token,
                                    @Body Keyring keyring);


        //Keys
        @POST("keys/create")
        Call<Key> createKey(@Query("authentification_token") String authentification_token,
                            @Query("uuid") String uuid,
                            @Body Key key);

        @DELETE("keys/delete")
        Call<Key> deleteKey(@Query("authentification_token") String authentification_token,
                            @Body Key key);
        @PUT("keys/update")
        Call<Key> updateKey(@Query("authentification_token") String authentification_token,
                            @Body Key key);



        @GET("keys/uploaded.json")
        Call<Key> keyUploaded(@Query("authentification_token") String authentification_token,
                              @Query("uuid") String uuid);

        //Locks
        @POST("locks/create")
        Call<Lock> createLock(@Query("authentification_token") String authentification_token,
                              @Body Lock lock);

        @PUT("locks/update")
        Call<Lock> updateLock(@Query("authentification_token") String authentification_token,
                              @Body Lock lock);

        @DELETE("locks/delete")
        Call<Lock> deleteLock(@Query("authentification_token") String authentification_token,
                              @Body Lock lock);

        //KeyLock
        @POST("keylock/create")
        Call<KeyLock> keyLockCreate(
                @Query("key") String key_uuid,
                @Query("lock") String lock_uuid);
    }

    public void createKey(Context context, final Key key) throws Exception {
        mKey = key;
        mContext = context;
        Keyring keyring = key.getKeyring();
        Call<Key> call = apiService.createKey(keyring.getUser().getAuthentification_token(), keyring.uuid(), key);

        Response<Key> response = call.execute();
        Integer statusCode = response.code();
        if (statusCode == HttpURLConnection.HTTP_OK || statusCode == HttpURLConnection.HTTP_CREATED) {
            mKey.uuid(response.body().uuid());
            mKey.saveThrows();
        } else {
            throw new RuntimeException("ERROR createKey: " + response.errorBody().string() + " : " + statusCode.toString());
        }

    }
    public void updateKey(Context context, Key key) throws IOException {
        mContext = context;
        mKey = key;
        Keyring keyring = key.getKeyring();

        Call<Key> call = apiService.updateKey(keyring.getUser().getAuthentification_token(), key);

        Response<Key> response = call.execute();
        Integer statusCode = response.code();
        if (statusCode == HttpURLConnection.HTTP_OK || statusCode == HttpURLConnection.HTTP_CREATED) {
            Log.i("updateKey", "success");
            mKeyring.uuid(response.body().uuid());
            mKeyring.saveThrows();
        } else {
            throw new RuntimeException("ERROR updateKey: " + response.errorBody().string() + " : " + statusCode.toString());
        }
    }


    public void deleteKey(Context context, Key key) throws IOException {
        mContext =context;
        mKey = key;
        Keyring keyring = key.getKeyring();

        Call<Key> call = apiService.deleteKey(keyring.getUser().getAuthentification_token(), key);

        Response<Key> response = call.execute();
        Integer statusCode = response.code();
        if (statusCode == HttpURLConnection.HTTP_OK || statusCode == HttpURLConnection.HTTP_CREATED) {
            Log.i("deleteKey", "success");
            mKeyring.uuid(response.body().uuid());
            mKeyring.saveThrows();
        } else {
            throw new RuntimeException("ERROR deleteKey: " + response.errorBody().string() + " : " + statusCode.toString());
        }
}



    public void createKeyring(Context context, Keyring keyring) throws IOException {
        mKeyring = keyring;
        mContext = context;
        Call<Keyring> call = apiService.createKeyring(keyring.getUser().getAuthentification_token(), keyring);

        Response<Keyring> response = call.execute();
        Integer statusCode = response.code();
        if (statusCode == HttpURLConnection.HTTP_OK || statusCode == HttpURLConnection.HTTP_CREATED) {
            Log.i("createKeyring", "success");
            mKeyring.uuid(response.body().uuid());
            mKeyring.saveThrows();
        } else {
            throw new RuntimeException("ERROR createKeyring: " + response.errorBody().string() + " : " + statusCode.toString());
        }
    }



    public void updateKeyring( Keyring keyring) throws IOException {

        Call<Keyring> call = apiService.updateKeyring(keyring.getUser().getAuthentification_token(), keyring);

        Response<Keyring> response = call.execute();
        int statusCode = response.code();
        if (statusCode == 200) {
            Log.i("MyKeysApidService", "::updatekeyring successful");
        } else {
            throw new RuntimeException("ERROR updateKeyring: " + response.errorBody().string() + " : " + String.valueOf(statusCode));
        }
    }

    public void deleteKeyring(Context context, Keyring keyring) throws IOException {

        Call<Keyring> call = apiService.deleteKeyring(keyring.getUser().getAuthentification_token(), keyring);

        Response<Keyring> response = call.execute();
        int statusCode = response.code();
        if (statusCode == 200) {
            Log.i("MyKeysApidService", "::deletekeyring successful");
        } else {
            throw new RuntimeException("ERROR deletekeyring: " + response.errorBody().string() + " : " + String.valueOf(statusCode));
        }
    }

    public void createLock(Context context, final Lock lock) throws IOException {
        mLock = lock;
        mContext = context;
        User user = mLock.getUser();
        Call<Lock> call = apiService.createLock(user.getAuthentification_token(), mLock);

        Response<Lock> response = call.execute();
        Integer statusCode = response.code();
        if (statusCode == HttpURLConnection.HTTP_OK || statusCode == HttpURLConnection.HTTP_CREATED) {
            mLock.uuid(response.body().uuid());
            mLock.saveThrows();
        } else {
            throw new RuntimeException("ERROR createLock: " + response.errorBody().string() + " : " + statusCode.toString());
        }
    }

    public void updateLock(Context context, final Lock lock) throws IOException {
        mLock = lock;
        mContext = context;
        User user = mLock.getUser();
        Call<Lock> call = apiService.updateLock(user.getAuthentification_token(), mLock);

        Response<Lock> response = call.execute();
        Integer statusCode = response.code();
        if (statusCode == HttpURLConnection.HTTP_OK || statusCode == HttpURLConnection.HTTP_CREATED) {
            mLock.uuid(response.body().uuid());
            mLock.saveThrows();
        } else {
            throw new RuntimeException("ERROR updateLock: " + response.errorBody().string() + " : " + statusCode.toString());
        }
    }


    public void deleteLock(Context context, final Lock lock) throws IOException {
        mLock = lock;
        mContext = context;
        User user = mLock.getUser();
        Call<Lock> call = apiService.deleteLock(user.getAuthentification_token(), mLock);

        Response<Lock> response = call.execute();
        Integer statusCode = response.code();
        if (statusCode == HttpURLConnection.HTTP_OK || statusCode == HttpURLConnection.HTTP_CREATED) {
            mLock.uuid(response.body().uuid());
            mLock.safeDelete();
        } else {
            throw new RuntimeException("ERROR deleteLock: " + response.errorBody().string() + " : " + statusCode.toString());
        }
    }




    public void keyUploaded(Context context, final Key key) throws Exception {
        mKey = key;
        mContext = context;
        Call<Key> call = apiService.keyUploaded(key.getKeyring().getUser().getAuthentification_token(), key.uuid());

        Response<Key> response = call.execute();
        int statusCode = response.code();
        if (statusCode == HttpURLConnection.HTTP_OK || statusCode == HttpURLConnection.HTTP_NO_CONTENT) {
            if (response.body().state().compareTo("StateRemoteKeyUploaded") != 0) {
                throw new RuntimeException("ERROR keyUploaded with wrong state: " + response.body().state());
            }
        } else {
            throw new RuntimeException("ERROR keyUploaded: " + response.errorBody().string() + " : " + String.valueOf(statusCode));
        }
    }

    public void createUser(User user) throws IOException {

        this.mUser = user;

        Call<User> call = apiService.createUser(user);

        Response<User> response = call.execute();
        int statusCode = response.code();
        if (statusCode == 200) {
            mUser.setAuthentification_token(response.body().getAuthentification_token());
            mUser.setS3Credential(response.body().getS3Credential());
            mUser.uuid(response.body().uuid());
            mUser.saveThrows();
        } else {
            throw new RuntimeException("ERROR createUser: " + response.errorBody().string() + " " + String.valueOf(statusCode));
        }
    }

    public void updateUser(User user) throws IOException {
        this.mUser = user;

        Call<User> call = apiService.updateUser(user);

        Response<User> response = call.execute();
        int statusCode = response.code();
        if (statusCode != 200) {
            throw new RuntimeException("ERROR updateUser: " + response.errorBody().string() + " : " + String.valueOf(statusCode));
        }
    }

    public void deleteTestUser() throws IOException {

        Call<User> call = apiService.deleteTestUser();
        Response<User> response = call.execute();
        int statusCode = response.code();
        if (statusCode == 200) {
            Log.i("MyKeysApidService", "::deleteUser successful");
        } else {
            Log.i("MyKeysApidService", "::deleteUser not found");
        }
    }






    public void createKeyLock(Context context, KeyLock keyLock) throws IOException {

        Call<KeyLock> call = apiService.keyLockCreate(keyLock.getKey().uuid(), keyLock.getLock().uuid());

        Response<KeyLock> response = call.execute();
        int statusCode = response.code();
        if (statusCode == 200) {
            Log.i("MyKeysApidService", "::createKeyLock successful");
        } else {
            throw new RuntimeException("ERROR createKeyLock: " + response.errorBody().string() + " : " + String.valueOf(statusCode));
        }
    }


    public PutObjectResult uploadKeyOnS3(Context context, User user, Key key) {
        PutObjectResult putResponse = null;
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                context,
                user.getS3Credential(), // Identity Pool ID
                Regions.US_EAST_1 // Region
        );

        AmazonS3Client s3Client = new AmazonS3Client(credentialsProvider);

        File fileToUpload = new File(key.localPath());
        boolean exist = fileToUpload.exists();

        if (key.uuid() == null)
            throw new RuntimeException("ERROR uploadKeyOnS3: " + "Key uuid not updated yet");

        String newFilePath = "a/b/c/d/e/" + key.uuid() + ".png";
        Log.i("uploadKeyOnS3: ", newFilePath);
        PutObjectRequest putRequest = new PutObjectRequest(
                ENV_SERVEUR,
                newFilePath,
                fileToUpload);
        putResponse = s3Client.putObject(putRequest);

        return putResponse;

    }

    public void downloadSmallKeyFromS3(Context context, User user, Key key) throws IOException {


        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                context,
                user.getS3Credential(), // Identity Pool ID
                Regions.US_EAST_1 // Region
        );

        AmazonS3Client s3Client = new AmazonS3Client(credentialsProvider);

        String s3FilePath = "a/b/c/d/e/" + key.uuid() + ".small.png";
        GetObjectRequest getRequest = new GetObjectRequest(
                ENV_SERVEUR,
                s3FilePath);

        S3Object response = s3Client.getObject(getRequest);
        InputStream myObjectBytes = response.getObjectContent();

        int content;
        FileOutputStream new_file = new FileOutputStream((key.localPath() + ".small.png"));
        while ((content = myObjectBytes.read()) != -1) {
            new_file.write(content);
        }
        new_file.close();
        myObjectBytes.close();
    }

    public void keyProfileDownloadFromS3(Context context, User user, Key key) throws Exception {

        String line = null;
        String[] content;
        com.mykeys.models.Profile profile;

        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                context,
                user.getS3Credential(), // Identity Pool ID
                Regions.US_EAST_1 // Region
        );

        AmazonS3Client s3Client = new AmazonS3Client(credentialsProvider);

        String remoteFilePath = user.uuid() + "/" + key.uuid() + ".profile";
        GetObjectRequest getRequest = new GetObjectRequest(ENV_SERVEUR, remoteFilePath);
        S3Object getResponse = s3Client.getObject(getRequest);
        InputStream myObjectBytes = getResponse.getObjectContent();
        GZIPInputStream gzipInputStream = new GZIPInputStream(myObjectBytes);

        BufferedReader reader = new BufferedReader(new InputStreamReader(gzipInputStream));

        while ((line = reader.readLine()) != null) {
            content = line.split(",");
            try {
                profile = new Profile(
                        key.getId(),
                        Float.parseFloat(content[0]),
                        Float.parseFloat((content[1])));
                profile.saveThrows();
            } catch (Exception ex) {
                Log.e("api", ex.getMessage());
            }
        }
        myObjectBytes.close();
    }

    public void keyExtremaDownloadFromS3(Context context, User user, Key key) throws Exception {

        String line = null;
        String[] content;
        com.mykeys.models.Extrema extrema;

        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                context,
                user.getS3Credential(), // Identity Pool ID
                Regions.US_EAST_1 // Region
        );

        AmazonS3Client s3Client = new AmazonS3Client(credentialsProvider);

        String remoteFilePath = user.uuid() + "/" + key.uuid() + ".extrema";
        GetObjectRequest getRequest = new GetObjectRequest(ENV_SERVEUR, remoteFilePath);
        S3Object getResponse = s3Client.getObject(getRequest);
        InputStream myObjectBytes = getResponse.getObjectContent();
        GZIPInputStream gzipInputStream = new GZIPInputStream(myObjectBytes);
        BufferedReader reader = new BufferedReader(new InputStreamReader(gzipInputStream));

        while ((line = reader.readLine()) != null) {
            content = line.split(",");
            try {
                extrema = new Extrema(
                        key.getId(),
                        Float.parseFloat(content[0]),
                        Float.parseFloat((content[1])));
                extrema.saveThrows();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        myObjectBytes.close();
    }
    */
}
