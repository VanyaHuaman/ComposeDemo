package com.example.composedemo.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.composedemo.navigation.NavRoutes
import com.example.composedemo.ui.screens.GenericListScreen
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
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        MyAppNavHost()
    }
}

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavRoutes.GENERIC_LIST_SCREEN.screenName
) {
    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavRoutes.GENERIC_LIST_SCREEN.screenName) {
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