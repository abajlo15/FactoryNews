package com.abajlo.factorynews.presenter;

import com.abajlo.factorynews.model.Article;
import com.abajlo.factorynews.model.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("articles?source=bbc-news&sortBy=top&apiKey=6946d0c07a1c4555a4186bfcade76398")
    Call<Data> getData();
}
