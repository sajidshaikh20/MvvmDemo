package com.learning.mvvmdemo.retrofitapiservice


import com.learning.mvvmdemo.model.QuoteClass
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {


    @GET("v1/quotes")
    suspend fun getData(): Response<QuoteClass>


//signup api
//    @FormUrlEncoded
//    @POST("api/v1/signup")
//    fun registerLogin (@FieldMap fields: Map<String, String?>): Call<RegisterationModel?>
//    @FormUrlEncoded
//    @POST("api/v1/signup")
//    fun registerLogin1 (@FieldMap fields: Map<String, String>): Call<RegisterResponseData>


//    //post data interface
//    @FormUrlEncoded
//    @POST("users")
//    fun createUser(
//        @FieldMap fields: Map<String, String?>
//    ): Call<PostDataClass>
//
//

//    @POST("register")
//    fun postRegister(@Body authModel: AuthModel): Call<AuthModel?>
}

