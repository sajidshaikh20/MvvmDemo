package com.learning.mvvmdemo.repositary


import com.learning.mvvmdemo.retrofitapiservice.ApiInterface

//
class QuateRepositary(private val apiInterface: ApiInterface) {

    suspend fun getQuates() = apiInterface.getData()

}