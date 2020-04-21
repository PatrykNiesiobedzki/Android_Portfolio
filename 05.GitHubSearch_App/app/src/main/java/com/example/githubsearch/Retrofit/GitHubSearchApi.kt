package com.example.githubsearch.Retrofit

import com.example.githubsearch.Model.GitHubSearchModelItems
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface SearchService {
    @GET("search/repositories?q=jajko")
    fun getSearchRepo() : Call<GitHubSearchModel>
}
class GitHubSearchModel{
    var items: List<GitHubSearchModelItems>? = null
}
class GitHubSearchApi {
    private val service : SearchService

    init {
        val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/").addConverterFactory(
            GsonConverterFactory.create()).build()
        service = retrofit.create(SearchService::class.java)
    }
    fun getSearchRepo(callback: Callback<GitHubSearchModel>){
        val call = service.getSearchRepo()
        call.enqueue(callback)
    }
}