package com.example.githubsearch

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.githubsearch.Adapters.ListAdapter
import com.example.githubsearch.Model.GitHubSearchModel
import com.example.githubsearch.Other.ScrollListener
import com.example.githubsearch.Retrofit.GitHubSearchApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val searchParameter = intent.extras?.getString("searchParameter")

        val searchList = findViewById<ListView>(R.id.searchList)
        searchList.setOnScrollListener(ScrollListener())

        val retriever = GitHubSearchApi()

        val callback = object : Callback<GitHubSearchModel> {
            override fun onFailure(call: Call<GitHubSearchModel>, t: Throwable) {
                title = "loll"
            }
            override fun onResponse(call: Call<GitHubSearchModel>?, response: Response<GitHubSearchModel>?) {
                title = "${response?.body()?.total_count.toString()} results for \" $searchParameter \""

                val repo = response?.body()?.items
                val repoString = mutableListOf<String>()
                if (repo !=null){
                    for (repos in repo){
                        val newString = repos.html_url
                        repoString.add(newString.toString())
                    }
                }
                val adapter = ListAdapter(this@ListActivity, android.R.layout.simple_list_item_1, repo)
                searchList.adapter = adapter

                searchList.setOnItemClickListener { parent, view, position, id ->
                    val webIntent = Intent(Intent.ACTION_VIEW,
                        Uri.parse(repoString[position])
                    )
                    startActivity(webIntent)
                }
            }
        }

        if (searchParameter != null){
            retriever.getSearchRepo(callback, searchParameter)
        }
    }
}
