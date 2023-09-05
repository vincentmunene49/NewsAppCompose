package com.loc.newsapp.ft_onboarding.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.loc.newsapp.R
import com.loc.newsapp.ft_onboarding.presentation.model.Page
import com.loc.newsapp.ui.LocalDimensions
import com.loc.newsapp.ui.LocalPadding
import com.loc.newsapp.ui.theme.NewsAppTheme
import com.loc.newsapp.ui.theme.on_boarding

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {
    val padding = LocalPadding.current
    val dimensions = LocalDimensions.current
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            modifier = modifier.fillMaxHeight(),
            painter = painterResource(id = R.drawable.la),
            contentDescription = "Premier league image",
            contentScale = ContentScale.Fit
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(color = on_boarding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = padding.extraLarge,
                        start = padding.large,
                        end = padding.large
                    ),
                verticalArrangement = Arrangement.spacedBy(dimensions.medium)
            ) {

                Text(text = "Hello There on boarding")
                Text(text = "Hello There on boarding")

            }

        }
    }
}

@Preview
@Composable
fun PreviewOnboardingComponent() {

    NewsAppTheme {
        OnBoardingPage(page = Page(
            "",
            "",
            R.drawable.prem
        ))
    }

}