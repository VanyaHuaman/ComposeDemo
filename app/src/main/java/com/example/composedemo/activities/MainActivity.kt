package com.example.composedemo.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composedemo.R
import com.example.composedemo.di.Modules
import com.example.composedemo.domain.models.BottomNavItem
import com.example.composedemo.navigation.BottomNavRoutes
import com.example.composedemo.ui.screens.GenericListScreen
import com.example.composedemo.ui.screens.HomeScreen
import com.example.composedemo.ui.theme.ComposeDemoTheme
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.compose.KoinApplication
import org.koin.core.annotation.KoinExperimentalAPI

class MainActivity : ComponentActivity() {

    @OptIn(KoinExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinAndroidContext() {
                ComposeDemoTheme {
                    AppRootComponent(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun AppRootComponent(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    MyAppNavHost(navController = navController)
}

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = BottomNavRoutes.HOME_SCREEN.route
) {
    var selectedIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val bottomNavItems = listOf(
        BottomNavItem(
            label = R.string.home,
            route = BottomNavRoutes.HOME_SCREEN.route,
            unselectedImage = Icons.Outlined.Home,
            selectedImage = Icons.Filled.Home,
        ),
        BottomNavItem(
            label = R.string.list,
            route = BottomNavRoutes.GENERIC_LIST_SCREEN.route,
            unselectedImage = Icons.Outlined.List,
            selectedImage = Icons.Filled.List,
        )
    )
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background,
    ) {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    bottomNavItems.forEachIndexed { index, bottomNavItem ->
                        val isSelected = index == selectedIndex
                        NavigationBarItem(
                            selected = isSelected,
                            label = {
                                Text(text = stringResource(id = bottomNavItem.label))
                            },
                            onClick = {
                                selectedIndex = index
                                navController.navigate(
                                    route = bottomNavItem.route,
                                    navOptions = NavOptions
                                        .Builder()
                                        .setPopUpTo(
                                            route = bottomNavItem.route,
                                            inclusive = true
                                        ).build()
                                )
                            },
                            icon = {
                                Icon(
                                    imageVector = if (isSelected) {
                                        bottomNavItem.selectedImage
                                    } else {
                                        bottomNavItem.unselectedImage
                                    },
                                    contentDescription = stringResource(id = bottomNavItem.label)
                                )
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                navController = navController,
                startDestination = startDestination
            ) {
                composable(BottomNavRoutes.HOME_SCREEN.route) {
                    HomeScreen()
                }
                composable(BottomNavRoutes.GENERIC_LIST_SCREEN.route) {
                    GenericListScreen(navController)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppRootComponentPreview() {
    KoinApplication(application = {
        // your preview config here
        modules(Modules.appModule, Modules.viewModelModule)
    }) {
        ComposeDemoTheme {
            AppRootComponent()
        }
    }
}