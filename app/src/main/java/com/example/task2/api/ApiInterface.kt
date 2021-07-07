package com.example.task2.api

import com.example.task2.model.responseModel.MainResponseModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("json")
    fun getProducts() : Call<MainResponseModel>

    companion object {

        private var BASE_URL = "https://stark-spire-93433.herokuapp.com/"

        fun create() : ApiInterface {
            val interceptor = HttpLoggingInterceptor()

            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}