package com.example.githubsearch.Retrofit

import com.example.githubsearch.Model.GitHubSearchModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("search/repositories")
    fun getSearchRepo(@Query("q") q : String, @Query("page") page : Int) : Call<GitHubSearchModel>
}

class GitHubSearchApi {
    private val service : SearchService

    init {
        val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/").addConverterFactory(
            GsonConverterFactory.create()).build()
        service = retrofit.create(SearchService::class.java)
    }
    fun getSearchRepo(callback: Callback<GitHubSearchModel>, searchParameter: String){
        val q = searchParameter
        val page = 0
        val call = service.getSearchRepo(q, page)
        call.enqueue(callback)
    }
}