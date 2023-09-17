package com.loc.newsapp.ft_onboarding.domain.useCases

import com.loc.newsapp.ft_onboarding.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingStatus(
    private val localUserManager: LocalUserManager
) {

    operator fun invoke(): Flow<Boolean>{
        return localUserManager.readAppEntry()
    }
}