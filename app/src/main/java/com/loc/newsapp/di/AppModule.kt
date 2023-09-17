package com.loc.newsapp.di

import android.app.Application
import com.loc.newsapp.ft_onboarding.data.manager.LocalUserManagerImpl
import com.loc.newsapp.ft_onboarding.domain.manager.LocalUserManager
import com.loc.newsapp.ft_onboarding.domain.useCases.OnBoardingStatus
import com.loc.newsapp.ft_onboarding.domain.useCases.ReadOnBoardingStatus
import com.loc.newsapp.ft_onboarding.domain.useCases.SaveOnBoardingStatus
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}