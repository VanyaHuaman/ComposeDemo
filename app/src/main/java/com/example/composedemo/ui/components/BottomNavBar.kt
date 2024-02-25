package com.example.composedemo.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.composedemo.navigation.BottomNavRoutes
import com.example.composedemo.ui.theme.ComposeDemoTheme

@Composable
fun BottomNavBar(navController: NavController?) {
    val navBackStackEntry = navController?.currentBackStackEntry
    val currentRoute = navBackStackEntry?.destination?.route
    val bottomNavItems = listOf(BottomNavRoutes.Home, BottomNavRoutes.GenericListScreen)

    ComposeDemoTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            bottomNavItems.forEach { item ->
                BottomNavigationItem(
                    selected = currentRoute == item.route,
                    onClick = {
                        navController?.navigate(item.route) {
                            popUpTo(item.route)
                            launchSingleTop = true
                        }
                    },
                    bottomNavRoutes = item,
                    Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavBarPreview() {
    BottomNavBar(navController = null)
}
