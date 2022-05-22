package com.example.elemestest.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
   companion object{
       val BASE_URL = "https://api.themoviedb.org/3/"
       val BASE_IMG_URL = "https://image.tmdb.org/t/p/w600_and_h900_bestv2"
       val API_KEY = "853447021eab275404740eed9ecaaf0d"
   }
    object ApiConnector{
        private  var retrofit: Retrofit ?= null
        val client: Retrofit? get(){
            if (retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
}