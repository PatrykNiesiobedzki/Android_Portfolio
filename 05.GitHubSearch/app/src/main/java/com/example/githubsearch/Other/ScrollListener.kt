package com.example.githubsearch.Other

import android.util.Log
import android.widget.AbsListView

class ScrollListener : AbsListView.OnScrollListener {

    private var visibleThreshold = 0
    private var currentPage = 0
    private var previousTotal = 0
    private var isScrolling = false

    override fun onScroll(view: AbsListView, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        if (isScrolling) {
            if (totalItemCount > previousTotal) {
                isScrolling = false
                previousTotal = totalItemCount
                currentPage++
            }
        }
        if (!isScrolling && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            Log.i("koniec", "koniec")
            isScrolling = true
        }
    }

    override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
        }
    }