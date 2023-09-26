package com.loc.newsapp.application_main.ft_news_navigation.domain.usecases

import androidx.paging.PagingData
import com.loc.newsapp.application_main.ft_news_navigation.domain.model.Article
import com.loc.newsapp.application_main.ft_news_navigation.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNewsUseCase(private val repository: NewsRepository) {
    operator fun invoke(sources:List<String>):Flow<PagingData<Article>>{
       return repository.getNews(sources = sources)
    }
}