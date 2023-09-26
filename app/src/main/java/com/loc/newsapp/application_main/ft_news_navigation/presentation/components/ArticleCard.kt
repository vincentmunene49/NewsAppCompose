package com.loc.newsapp.application_main.ft_news_navigation.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.loc.newsapp.R
import com.loc.newsapp.application_main.ft_news_navigation.domain.model.Article
import com.loc.newsapp.application_main.ft_news_navigation.domain.model.Source
import com.loc.newsapp.ui.LocalDimensions
import com.loc.newsapp.ui.LocalPadding
import com.loc.newsapp.ui.theme.NewsAppTheme

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit
) {

    Row(modifier = modifier.fillMaxWidth().clickable { onClick() }) {

        AsyncImage(
            modifier = Modifier
                .size(LocalDimensions.current.cardSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(LocalContext.current)
                .data(article.urlToImage)
                .build(),
            contentDescription = stringResource(R.string.articleImageDescription),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .height(LocalDimensions.current.cardSize)
                .padding(LocalPadding.current.small),
            verticalArrangement = Arrangement.SpaceAround
        ) {

            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.text_title),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body)
                )
                Spacer(modifier = Modifier.width(LocalDimensions.current.smallSpacing))

                Icon(
                    modifier = Modifier.size(LocalDimensions.current.medium2),
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = "",
                    tint = colorResource(id = R.color.body)
                )
                Spacer(modifier = Modifier.width(LocalDimensions.current.smallSpacing))

                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body)
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCard() {
    NewsAppTheme {
        ArticleCard(
            article = Article(
                author = "James",
                content = "",
                description = "",
                publishedAt = "2 hours",
                source = Source(id = "", name = "bbc-news"),
                title = "Sancho has been suspended from all manchester united activities and the starting 11 after a collision with Ten Hag. The young England nationality came out to question the manager after the manager threw shade on him",
                url = "",
                urlToImage = ""
            ),
            onClick = {}
        )
    }
}