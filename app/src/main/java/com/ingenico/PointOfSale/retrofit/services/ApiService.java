package com.ingenico.PointOfSale.retrofit.services;

import com.ingenico.PointOfSale.Object.VoidTransaction;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ILM on 6/29/2016.
 */
public interface ApiService {
    //-----------------------------------Phase 0.1--------------------------------------------------

    //Void Transaction
    @FormUrlEncoded
    @POST("api/pos/void_transact.php")
    Call<VoidTransaction> voidTransaction(@Field("kode") String kode,
                                          @Field("username") String username,
                                          @Field("password") String password,
                                          @Field("transact_id") String transactId);

}
