package com.learning.mvvmdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.learning.mvvmdemo.repositary.QuateRepositary

class QuateViewModelFactory(private val quatesRepositary: QuateRepositary): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(quatesRepositary) as T
    }
}
