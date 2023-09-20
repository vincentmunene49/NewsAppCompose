package com.loc.newsapp.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.newsapp.ft_onboarding.domain.useCases.OnBoardingStatus
import com.loc.newsapp.ft_onboarding.presentation.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val onBoardingStatus: OnBoardingStatus
) : ViewModel() {

    var startingDestination by mutableStateOf(Routes.AppStartNavigation.route)
        private set

    var splashCondition by mutableStateOf(true)
        private set

    init {
        onBoardingStatus.readOnBoardingStatus().onEach {
            startingDestination = if(it){
                Routes.NewsNavigation.route
            }else{
                Routes.AppStartNavigation.route
            }

            delay(300)
            splashCondition = false

        }.launchIn(viewModelScope)
    }
}