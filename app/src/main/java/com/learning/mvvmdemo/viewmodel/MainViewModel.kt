package com.learning.mvvmdemo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.mvvmdemo.model.QuoteClass
import com.learning.mvvmdemo.repositary.QuateRepositary

import com.learning.mvvmdemo.retrofitapiservice.ApiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val quateRepositary: QuateRepositary) : ViewModel() {

    val quatelivedata = MutableLiveData("Click button to Change data")

    val counter: MutableLiveData<Int> = MutableLiveData(0)

    val quates = MutableLiveData("Own business is own business non of your business")


    fun updateQuate() {
        quatelivedata.value = "you will see it when  you beleive"
    }
    fun incrementCounter() {
        val currentValue = counter.value ?: 0
        counter.value = currentValue + 1
    }
    fun decreament() {
        val currentData = counter.value ?: 0
        counter.value = currentData - 1
    }

    fun updaterQuates() {
        val currentQuates = quates.value
        viewModelScope.launch(Dispatchers.IO) {

            val response = quateRepositary.getQuates()
            val data = response.body()
            quates.postValue(
                data?.get(0)?.quote.toString()
            )
            //delay(1000L)
            Log.i("data", "api call")
        }
    }

}


