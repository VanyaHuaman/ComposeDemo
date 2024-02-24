package com.example.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.theme.ComposeDemoTheme

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
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
    ) {
        Text(
            text = title,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        ElevatedButton(
            onClick = { /* TODO */ },
            modifier = Modifier
        ) {
            Text(
                text = "Show more"
            )
        }
    }
}

@Composable
fun MainActivityScreen(
    modifier: Modifier = Modifier
) {
    GenericListComponent(
        titles = listOf("title 1", "title 2"),
        modifier
            .fillMaxWidth()
            .padding(24.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun AppRootComponentPreview() {
    ComposeDemoTheme {
        AppRootComponent()
    }
}