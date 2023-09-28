package com.loc.newsapp.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.loc.newsapp.ft_news_navigation.domain.model.Article
import com.loc.newsapp.ft_news_navigation.presentation.components.ArticleCard
import com.loc.newsapp.ui.LocalDimensions

@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit
) {

    val handleResponse = handleResponse(articles = articles)

    if (handleResponse) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(LocalDimensions.current.medium2),
            verticalArrangement = Arrangement.spacedBy(LocalDimensions.current.medium)
        ) {

            items(count = articles.itemCount) {
                articles[it]?.let { article ->
                    ArticleCard(article = article, onClick = { onClick(article) })
                }
            }
        }
    }
}


@Composable
fun handleResponse(
    articles: LazyPagingItems<Article>
): Boolean {

    val loadState = articles.loadState

    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(error)
            false
        }

        else -> true

    }
}

@Composable
private fun ShimmerEffect() {
    Column(
        verticalArrangement = Arrangement.spacedBy(LocalDimensions.current.medium)
    ) {
        repeat(10) {
            ArticleCardShimmerEffect(modifier = Modifier.padding(LocalDimensions.current.medium))
        }

    }
}