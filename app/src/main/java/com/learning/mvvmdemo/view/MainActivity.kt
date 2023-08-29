package com.learning.mvvmdemo.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.learning.mvvmdemo.R
import com.learning.mvvmdemo.databinding.ActivityMainBinding
import com.learning.mvvmdemo.repositary.QuateRepositary
import com.learning.mvvmdemo.retrofitapiservice.ApiHelper
import com.learning.mvvmdemo.retrofitapiservice.ApiInterface
import com.learning.mvvmdemo.viewmodel.MainViewModel
import com.learning.mvvmdemo.viewmodel.QuateViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    private val TAG: String = "Kotlinfun"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val quoteService = ApiHelper.getApi()
        val repository = QuateRepositary(quoteService)

        //one way binding demo
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        Log.d(TAG, "${Thread.currentThread().name}")

        mainViewModel = ViewModelProvider(
            this,
            QuateViewModelFactory(repository)
        ).get(MainViewModel::class.java)

        mainViewModel.quates.observe(this) {
            binding.quotes = it
        }

        //live data unhi ko data bhejte hai jo unki active state me hote hai
        mainViewModel.quatelivedata.observe(this, { binding.tvquatetext.text = it })
        mainViewModel.counter.observe(this, { binding.tvCounter.text })

        binding.mainviewModel = mainViewModel
        binding.lifecycleOwner = this


    }

    fun executeLongRunningTask() {
        Log.d("thread", "${Thread.currentThread().name}")
        for (i in 1..1000000000L) {

        }
    }

    fun updateCounter(view: View) {
        Log.d(TAG, "${Thread.currentThread().name}")
        binding.tvCounterCoroutine.text =
            "${binding.tvCounterCoroutine.text.toString().toInt() + 1}"
    }

    fun doAction(view: View) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d(TAG, "1 -${Thread.currentThread().name}")
            executeLongRunningTask()
        }
        GlobalScope.launch(Dispatchers.Main) {
            Log.d(TAG, "2-${Thread.currentThread().name}")
        }
        MainScope().launch(Dispatchers.Default) {
            Log.d(TAG, "3-${Thread.currentThread().name}")
        }
    }
}

