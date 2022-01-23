package com.eudocode.firedrop.network;

import com.eudocode.firedrop.model.ProductResponseModel;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServerApi {

    @POST("v1/customer/home")
    Call<ProductResponseModel> getHomeData(@Query(value = "api_key") String api_key);
}
