package com.loc.newsapp.application_main.ft_news_navigation.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.loc.newsapp.application_main.ft_news_navigation.data.remote.NewsApi
import com.loc.newsapp.application_main.ft_news_navigation.data.remote.NewsPagingSource
import com.loc.newsapp.application_main.ft_news_navigation.domain.model.Article
import com.loc.newsapp.application_main.ft_news_navigation.domain.repository.NewsRepository
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
}