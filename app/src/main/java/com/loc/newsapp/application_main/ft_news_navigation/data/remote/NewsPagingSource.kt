package com.loc.newsapp.application_main.ft_news_navigation.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.loc.newsapp.application_main.ft_news_navigation.domain.model.Article
import com.loc.newsapp.util.constants.TOKEN

class NewsPagingSource(
    private val api: NewsApi,
    private val source: String,

    ) : PagingSource<Int, Article>() {
    private var totalResults = 0
    private val token = "Bearer $TOKEN"
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1

        return try {
            val response = api.getNews(source = source, page = page, token = token)
            totalResults += response.articles.size


        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        TODO("Not yet implemented")
    }


}