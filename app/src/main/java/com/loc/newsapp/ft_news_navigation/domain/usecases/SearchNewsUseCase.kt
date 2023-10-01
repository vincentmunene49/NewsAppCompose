package com.loc.newsapp.ft_news_navigation.domain.usecases

import androidx.paging.PagingData
import com.loc.newsapp.ft_news_navigation.domain.model.Article
import com.loc.newsapp.ft_news_navigation.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNewsUseCase (private val repository: NewsRepository){

    operator fun invoke(sources:List<String>, searchQuery:String): Flow<PagingData<Article>> {
        return repository.searchNews(sources = sources, searchQuery = searchQuery)
    }
}