package com.loc.newsapp.ft_onboarding.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.newsapp.ft_onboarding.domain.useCases.OnBoardingStatus
import com.loc.newsapp.ft_onboarding.presentation.OnBoardingEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
   private val onBoardingStatus: OnBoardingStatus
) : ViewModel(
) {

    fun onBoardingEvent(event:OnBoardingEvents){
        when (event){
            is OnBoardingEvents.SaveOnBoardingStatus ->{
                    saveOnBoardingStatus()
            }
        }
    }

    private fun saveOnBoardingStatus() {
        viewModelScope.launch {
            onBoardingStatus.saveOnBoardingStatus()
        }
    }

}