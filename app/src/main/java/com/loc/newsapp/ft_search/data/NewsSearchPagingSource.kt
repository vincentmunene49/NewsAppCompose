package com.loc.newsapp.ft_search.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.loc.newsapp.ft_news_navigation.data.remote.NewsApi
import com.loc.newsapp.ft_news_navigation.domain.model.Article
import com.loc.newsapp.util.constants.TOKEN

class NewsSearchPagingSource(
    private val api: NewsApi,
    private val searchQuery: String,
    private val source: String
) : PagingSource<Int, Article>() {
    private var totalResults = 0
    private val token = "Bearer $TOKEN"
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1

        return try {
            val response =
                api.searchNews(sources = source, token = token, searchQuery = searchQuery)
            totalResults += response.articles.size

            val articles = response.articles.distinctBy { it.title }
            val previousPageKey = if (totalResults > 0) page - 1 else null
            val nextPageKey = if (totalResults <= 100) page + 1 else null

            LoadResult.Page(
                data = articles,
                prevKey = previousPageKey,
                nextKey = nextPageKey
            )

        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }
}