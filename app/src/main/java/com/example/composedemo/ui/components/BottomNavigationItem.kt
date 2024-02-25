package com.example.composedemo.ui.components

import android.content.res.Resources.Theme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.navigation.BottomNavRoutes
import com.example.composedemo.ui.theme.ComposeDemoTheme

@Composable
fun BottomNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    bottomNavRoutes: BottomNavRoutes,
    modifier: Modifier = Modifier
) {
    ComposeDemoTheme {
        Column(
            modifier = modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .padding(8.dp)
                .clickable {
                    onClick()
                }
        ) {
            Icon(
                painter = painterResource(id = bottomNavRoutes.icon),
                contentDescription = stringResource(id = bottomNavRoutes.label),
                tint = if (selected) MaterialTheme.colorScheme.primary else Color.Black,
                modifier = Modifier.align(
                    alignment = Alignment.CenterHorizontally
                )
            )
            Text(stringResource(id = bottomNavRoutes.label), color = if (selected) MaterialTheme.colorScheme.primary else Color.Black)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BottomNavigationItemPreview() {
    BottomNavigationItem(true, {}, BottomNavRoutes.Home)
}