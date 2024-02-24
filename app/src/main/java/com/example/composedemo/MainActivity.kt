package com.example.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.theme.ComposeDemoTheme
import com.example.composedemo.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                AppRootComponent(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun AppRootComponent(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        MainActivityScreen()
    }
}

@Composable
fun GenericListComponent(
    titles: List<String>,
    modifier: Modifier = Modifier,
    itemSpacing: Int = 4
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(itemSpacing.dp),
        modifier = modifier
    ) {
        items(titles) {
            GenericListItem(title = it)
        }
    }
}

@Composable
fun GenericListItem(
    title: String,
    modifier: Modifier = Modifier
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .background(MaterialTheme.colorScheme.onTertiaryContainer)
    ) {
        Column(
            modifier.padding(12.dp)
        ) {
            Row {
                Text(
                    text = title,
                    style = Typography.titleMedium,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )
                ElevatedButton(
                    onClick = { expanded = !expanded },
                    modifier = Modifier
                ) {
                    Text(
                        text = if (expanded) "Show less" else "Show more",
                        style = Typography.titleSmall
                    )
                }
            }
            if (expanded) {
                Box(
                    modifier = Modifier
                        .padding(vertical = 24.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Message Text"
                    )
                }
            }
        }
    }
}

@Composable
fun MainActivityScreen(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary,
    ) {
        GenericListComponent(
            titles = listOf("title 1", "title 2"),
            Modifier
                .fillMaxWidth()
                .padding(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppRootComponentPreview() {
    ComposeDemoTheme {
        AppRootComponent()
    }
}