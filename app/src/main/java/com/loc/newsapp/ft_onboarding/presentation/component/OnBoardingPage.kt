package com.loc.newsapp.ft_onboarding.presentation.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.loc.newsapp.R
import com.loc.newsapp.ft_onboarding.presentation.model.Page
import com.loc.newsapp.ft_onboarding.presentation.model.pages
import com.loc.newsapp.ui.LocalPadding
import com.loc.newsapp.ui.theme.NewsAppTheme

@Composable
fun OnBoardingPage(
    page: Page,
    modifier: Modifier = Modifier,

    ) {
    Column(
        modifier = modifier
    ) {

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .padding(bottom = LocalPadding.current.extraLarge),
            painter = painterResource(id = page.image),
            contentDescription = "onBoarding page",
            contentScale = ContentScale.Crop
        )

        Text(
            modifier = Modifier.padding(horizontal = LocalPadding.current.extraLarge),
            text = page.title,
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.display_small)
        )
        Text(
            modifier = Modifier.padding(horizontal = LocalPadding.current.extraLarge),
            text = page.description,
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.text_medium)
        )
    }


}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewOnboardingComponent() {

    NewsAppTheme {

        OnBoardingPage(page = pages[0])

    }

}