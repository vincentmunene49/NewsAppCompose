package com.loc.newsapp.ft_news_navigation.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.loc.newsapp.ft_news_navigation.domain.usecases.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCase: NewsUseCases
) : ViewModel() {

    val news = newsUseCase.getNewsUseCase(sources = listOf("abc-news", "cnn", "bbc-news")).cachedIn(viewModelScope)


}