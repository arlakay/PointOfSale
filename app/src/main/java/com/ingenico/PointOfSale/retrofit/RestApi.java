package com.ingenico.PointOfSale.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ILM on 6/29/2016.
 */
public class RestApi {
    public static final String SUPERBASE_URL = "http://182.23.81.179/";
//    public static final String SUPERBASE_URL = "http://192.168.0.101/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(SUPERBASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
