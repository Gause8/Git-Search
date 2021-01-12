package com.example.viperdesignchallenge.data.network

import com.example.viperdesignchallenge.data.network.response.GitResponse
import com.example.viperdesignchallenge.data.network.response.SearchResponse
import com.example.viperdesignchallenge.data.network.response.UserResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.logging.Level


interface GitApi {
    @GET("users/{name}/repos")
    suspend fun getUserRepos(@Path("name") name: String): Response<GitResponse>
    @GET("search/users")
    suspend fun getUserSearch(@Query("q") search: String): Response<SearchResponse>

    companion object{
        operator fun invoke(): GitApi{
            val logging = HttpLoggingInterceptor()

            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            return Retrofit
                .Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(GitApi::class.java)
        }
    }
}