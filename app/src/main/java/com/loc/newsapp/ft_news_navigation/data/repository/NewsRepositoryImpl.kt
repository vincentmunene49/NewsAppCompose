package com.loc.newsapp.ft_news_navigation.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.loc.newsapp.ft_news_navigation.data.remote.NewsApi
import com.loc.newsapp.ft_news_navigation.data.remote.NewsPagingSource
import com.loc.newsapp.ft_news_navigation.domain.model.Article
import com.loc.newsapp.ft_news_navigation.domain.repository.NewsRepository
import com.loc.newsapp.ft_search.data.NewsSearchPagingSource
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val api: NewsApi
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),//10 articles in each request
            pagingSourceFactory = {
                NewsPagingSource (
                    api = api,
                    source = sources.joinToString(",")
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String,sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),//10 articles in each request
            pagingSourceFactory = {
                NewsSearchPagingSource (
                    api = api,
                    source = sources.joinToString(","),
                    searchQuery = searchQuery
                )
            }
        ).flow
    }
}