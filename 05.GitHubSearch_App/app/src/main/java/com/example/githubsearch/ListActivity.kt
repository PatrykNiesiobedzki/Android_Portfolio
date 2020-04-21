package com.example.githubsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.githubsearch.Adapters.ListAdapter
import com.example.githubsearch.Retrofit.GitHubSearchApi
import com.example.githubsearch.Retrofit.GitHubSearchModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val searchList = findViewById<ListView>(R.id.searchList)

        val retriver = GitHubSearchApi()

        val callback = object : Callback<GitHubSearchModel> {
            override fun onFailure(call: Call<GitHubSearchModel>, t: Throwable) {
                title = "loll"
            }
            override fun onResponse(call: Call<GitHubSearchModel>, response: Response<GitHubSearchModel>) {

                val repo = response.body()?.items as MutableList

                val adapter = ListAdapter(this@ListActivity, android.R.layout.simple_list_item_1, repo)
                searchList.adapter = adapter
            }
        }

        retriver.getSearchRepo(callback)
    }
}
