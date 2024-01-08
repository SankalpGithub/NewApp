package com.example.shopingapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RetrofitAPI {
        @GET
         Call<newsmodal>getAllNews(@Url String url);
    }

