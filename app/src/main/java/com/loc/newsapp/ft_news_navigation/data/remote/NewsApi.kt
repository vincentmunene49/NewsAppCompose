package com.loc.newsapp.ft_news_navigation.data.remote

import com.loc.newsapp.ft_news_navigation.data.remote.dto.NewsResponse
import com.loc.newsapp.util.constants.BASEURL
import com.loc.newsapp.util.constants.TOKEN
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getNews(
        @Header("Authorization") token:String,
        @Query("sources") sources: String
    ): NewsResponse
}