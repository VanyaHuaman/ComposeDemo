package com.example.composedemo.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composedemo.di.Modules
import com.example.composedemo.ui.components.BottomNavBar
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
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)) {
                MyAppNavHost(navController = navController)
            }
            Row {
                BottomNavBar(navController = navController)
            }
        }
    }
}

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = BottomNavRoutes.Home.route
) {
    NavHost(
        modifier = modifier
            .fillMaxSize(),
        navController = navController,
        startDestination = startDestination
    ) {
        composable(BottomNavRoutes.Home.route) {
            HomeScreen(navController)
        }
        composable(BottomNavRoutes.GenericListScreen.route) {
            GenericListScreen(navController)
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