package com.loc.newsapp.ft_onboarding.domain.useCases

import com.loc.newsapp.ft_onboarding.domain.manager.LocalUserManager

class SaveOnBoardingStatus(private val localUserManager: LocalUserManager) {

    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}