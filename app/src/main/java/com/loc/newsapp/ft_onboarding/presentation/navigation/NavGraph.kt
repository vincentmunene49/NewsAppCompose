package com.loc.newsapp.ft_onboarding.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.loc.newsapp.ft_news_navigation.presentation.home.HomeScreenContent
import com.loc.newsapp.ft_news_navigation.presentation.home.HomeViewModel
import com.loc.newsapp.ft_onboarding.presentation.OnBoardingScreenContent
import com.loc.newsapp.ft_onboarding.presentation.viewModel.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navigation(
            startDestination = Routes.OnBoardingScreen.route,
            route = Routes.AppStartNavigation.route
        ) {
            composable(route = Routes.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreenContent(event = viewModel::onBoardingEvent)
            }
        }

        navigation(
            startDestination = Routes.NewsNavigatorScreen.route,
            route = Routes.NewsNavigation.route
        ) {
            composable(route = Routes.NewsNavigatorScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()

                HomeScreenContent(articles = articles, navigate = {})
            }
        }

    }
}