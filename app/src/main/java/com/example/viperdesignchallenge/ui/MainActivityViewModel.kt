package com.example.viperdesignchallenge.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.viperdesignchallenge.data.repositories.GitRepository
import com.example.viperdesignchallenge.util.Coroutines

private const val TAG = "MainActivityViewModel"

class MainActivityViewModel : ViewModel() {
    var mainListener: MainListener? = null

    fun onSearch(inputSearch: String) {
        Log.d(TAG, "onSearch: ")
        Coroutines.main {
            val response = GitRepository().getUserSearch(inputSearch)
            response.body()?.items?.let {
                mainListener?.onSuccess(it)
            }
        }

    }


}

