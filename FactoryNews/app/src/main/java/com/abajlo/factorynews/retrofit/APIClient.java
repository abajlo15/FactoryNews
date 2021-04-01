package com.abajlo.factorynews.retrofit;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class APIClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context context) {

        NetworkInterceptor networkInterceptor = new NetworkInterceptor(context);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(networkInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v1/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}
