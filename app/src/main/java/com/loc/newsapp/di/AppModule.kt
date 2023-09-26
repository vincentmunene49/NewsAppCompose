package com.loc.newsapp.di

import android.app.Application
import com.loc.newsapp.ft_news_navigation.data.remote.NewsApi
import com.loc.newsapp.ft_news_navigation.data.repository.NewsRepositoryImpl
import com.loc.newsapp.ft_news_navigation.domain.repository.NewsRepository
import com.loc.newsapp.ft_news_navigation.domain.usecases.GetNewsUseCase
import com.loc.newsapp.ft_news_navigation.domain.usecases.NewsUseCases
import com.loc.newsapp.ft_onboarding.data.manager.LocalUserManagerImpl
import com.loc.newsapp.ft_onboarding.domain.manager.LocalUserManager
import com.loc.newsapp.ft_onboarding.domain.useCases.OnBoardingStatus
import com.loc.newsapp.ft_onboarding.domain.useCases.ReadOnBoardingStatus
import com.loc.newsapp.ft_onboarding.domain.useCases.SaveOnBoardingStatus
import com.loc.newsapp.util.constants.BASEURL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager =
        LocalUserManagerImpl(application)


    @Provides
    @Singleton
    fun provideOnBoardingStatus(localUserManager: LocalUserManager) = OnBoardingStatus(
        readOnBoardingStatus = ReadOnBoardingStatus(localUserManager),
        saveOnBoardingStatus = SaveOnBoardingStatus(localUserManager)
    )

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASEURL)
        .build()

    @Provides
    @Singleton
    fun provideNewsApiService(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(api: NewsApi): NewsRepository {
        return NewsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(repository: NewsRepository): NewsUseCases {
        return NewsUseCases(
            getNewsUseCase = GetNewsUseCase(repository)
        )
    }
}