package com.example.viperdesignchallenge.ui

import com.example.viperdesignchallenge.data.network.response.GitResponse
import com.example.viperdesignchallenge.data.network.response.Item
import com.example.viperdesignchallenge.data.network.response.UserResponse
import retrofit2.Response

interface MainListener {
    fun onStarted()
    fun onSuccess(response: List<Item>)
    fun onFailure(message: String)
    fun inflateSearchFragment(dataset: List<UserResponse>)
    fun inflateDetailedView(dataItem: Item)
}