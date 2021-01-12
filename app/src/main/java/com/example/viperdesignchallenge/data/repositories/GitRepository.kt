package com.example.viperdesignchallenge.data.repositories

import com.example.viperdesignchallenge.data.network.GitApi
import com.example.viperdesignchallenge.data.network.response.GitResponse
import com.example.viperdesignchallenge.data.network.response.SearchResponse
import com.example.viperdesignchallenge.data.network.response.UserResponse
import retrofit2.Response


class GitRepository {

    suspend fun getRepository(name: String): Response<GitResponse> {
        return GitApi().getUserRepos(name)
    }

    suspend fun getUserSearch(userQuery: String): Response<SearchResponse> {
        return GitApi().getUserSearch(userQuery)
    }
}