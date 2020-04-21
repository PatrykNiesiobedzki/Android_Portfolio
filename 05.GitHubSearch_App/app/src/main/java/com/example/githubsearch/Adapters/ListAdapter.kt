package com.example.githubsearch.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.githubsearch.Model.GitHubSearchModelItems
import com.example.githubsearch.R

class ListAdapter (context: Context, resource: Int, objects: List<GitHubSearchModelItems>?) : ArrayAdapter<GitHubSearchModelItems>(context, resource, objects!!) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val repoView = inflator.inflate(R.layout.repo_list, parent, false)

        val avatar = repoView.findViewById<ImageView>(R.id.avatar)
        val author = repoView.findViewById<TextView>(R.id.author)
        val rating = repoView.findViewById<TextView>(R.id.rating)

        val listResults = getItem(position)
        author.text = listResults?.name
        return repoView
    }
}