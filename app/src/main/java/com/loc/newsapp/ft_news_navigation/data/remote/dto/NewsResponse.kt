package com.loc.newsapp.ft_news_navigation.data.remote.dto

import com.loc.newsapp.ft_news_navigation.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)