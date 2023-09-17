package com.loc.newsapp.ft_onboarding.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.loc.newsapp.common.NewsButton
import com.loc.newsapp.common.NewsTextButton
import com.loc.newsapp.ft_onboarding.presentation.component.OnBoardingPage
import com.loc.newsapp.ft_onboarding.presentation.component.PageIndicator
import com.loc.newsapp.ft_onboarding.presentation.model.pages
import com.loc.newsapp.ui.LocalPadding
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreenContent(
    event: (OnBoardingEvents) -> Unit
) {

    val pagesList = pages

    val scope = rememberCoroutineScope()

    val pagerState = rememberPagerState(initialPage = 0) {
        pagesList.size
    }

    val buttonState = remember {
        derivedStateOf {
            when (pagerState.currentPage) {
                0 -> listOf("", "Next")
                pagesList.size - 1 -> listOf("Back", "Get Started")
                else -> listOf("Back", "Next")
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(page = pagesList[index])
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LocalPadding.current.extraLarge)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(
                modifier = Modifier.width(52.dp),
                pageSize = pagesList.size,
                selectedPage = pagerState.currentPage
            )

            Row {
                if (buttonState.value[0].isNotEmpty()) {
                    NewsTextButton(title = buttonState.value[0]) {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                }

                NewsButton(title = buttonState.value[1]) {
                    if (pagerState.currentPage == pagesList.size-1) {
                        event(OnBoardingEvents.SaveOnBoardingStatus)
                    } else {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(0.5f))

    }
}