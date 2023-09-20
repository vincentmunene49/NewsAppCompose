package com.loc.newsapp.ft_onboarding.presentation.navigation

sealed class Routes(val route:String)
{
    object OnBoardingScreen:Routes("onBoardingScreens")
    object HomeScreen:Routes("homeScreen")
    object SearchScreen:Routes("searchScreen")
    object BookMarkScreen:Routes("bookMarkScreen")
    object DetailsScreen:Routes("detailsScreen")

    /*Sub-navigation*/

    object AppStartNavigation:Routes("startApplicationSubNavigation")
    object NewsNavigatorScreen:Routes("newsNavigator")
    object NewsNavigation:Routes("newsNavigation")
}