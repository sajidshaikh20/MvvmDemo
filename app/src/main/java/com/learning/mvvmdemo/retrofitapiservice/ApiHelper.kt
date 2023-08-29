package com.learning.mvvmdemo.retrofitapiservice

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiHelper private constructor() {


    companion object {

        var BASE_URL = "https://quotes-by-api-ninjas.p.rapidapi.com/"

        private val api: ApiInterface

        init {
            val builder = OkHttpClient.Builder()
            builder.addInterceptor(AuthIntercepter())

            val retrofit =
                Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).client(builder.build())
                    .build()
            api = retrofit.create(ApiInterface::class.java)
        }

        fun getApi(): ApiInterface {
            return api
        }

        @get:Synchronized
        var instance: ApiHelper? = null
            get() {
                if (field == null) {
                    field = ApiHelper()
                }
                return field
            }
            private set
    }
}

