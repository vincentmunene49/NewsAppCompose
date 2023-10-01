package com.loc.newsapp.ft_news_navigation.domain.repository

import androidx.paging.PagingData
import com.loc.newsapp.ft_news_navigation.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(sources:List<String>):Flow<PagingData<Article>>

    fun searchNews(searchQuery:String,sources: List<String>):Flow<PagingData<Article>>
}