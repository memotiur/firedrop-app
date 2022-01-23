package com.eudocode.firedrop.network;

import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitIntence {

    public static final String BASE_URL = "https://firebox.com.bd/api/";
    public static final String IMG_BASE_URL = "https://firebox.com.bd";

    static retrofit2.Retrofit retrofit;

    public static retrofit2.Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    /*   .client(getUnsafeOkHttpClient().build())*/
                    .build();
        }

        return retrofit;
    }
}
