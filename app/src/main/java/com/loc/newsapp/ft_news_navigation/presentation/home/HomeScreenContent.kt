package com.loc.newsapp.ft_news_navigation.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.loc.newsapp.R
import com.loc.newsapp.common.ArticleList
import com.loc.newsapp.ft_news_navigation.domain.model.Article
import com.loc.newsapp.ft_news_navigation.presentation.components.SearchBar
import com.loc.newsapp.ft_onboarding.presentation.navigation.Routes
import com.loc.newsapp.ui.LocalDimensions
import com.loc.newsapp.ui.LocalPadding

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenContent(
    articles: LazyPagingItems<Article>,
    navigate: (String) -> Unit
) {

    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items
                    .slice(
                        IntRange(
                            start = 0,
                            endInclusive = 9
                        )
                    )
                    .joinToString(separator = "\uD83d\uDFE5") { it.title }
            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = LocalPadding.current.extraLarge)
            .statusBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(LocalDimensions.current.extraLarge)

    ) {
        Image(
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(LocalPadding.current.medium),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = ""
        )

        SearchBar(
            modifier = Modifier.padding(horizontal = LocalPadding.current.extraLarge),
            onValueChange = {},
            onSearch = { /*TODO*/ },
            onClickSearchView = { navigate(Routes.SearchScreen.route) },
            text = "",
            readOnly = true
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = LocalPadding.current.extraLarge)
                .basicMarquee(),
            text = titles
        )

        ArticleList(
            modifier = Modifier.padding(horizontal = LocalPadding.current.extraLarge),
            articles = articles,
            onClick = {
                navigate(Routes.DetailsScreen.route)
            }
        )
    }

}