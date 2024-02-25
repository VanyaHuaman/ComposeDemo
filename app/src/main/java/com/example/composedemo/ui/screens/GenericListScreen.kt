package com.example.composedemo.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.di.Modules
import com.example.composedemo.ui.components.GenericListComponent
import com.example.composedemo.ui.theme.ComposeDemoTheme
import com.example.composedemo.viewmodels.GenericListScreenViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication

@Composable
fun GenericListScreen(
    navHostController: NavHostController?,
    modifier: Modifier = Modifier,
    viewModel: GenericListScreenViewModel = koinViewModel()
) {
    val listScreenState = viewModel.genericListScreenState.collectAsState()
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary,
    ) {
        GenericListComponent(
            listState = listScreenState.value.genericListState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GenericListScreenPreview() {
    KoinApplication(application = {
        // your preview config here
        modules(Modules.appModule, Modules.viewModelModule)
    }) {
        ComposeDemoTheme {
            GenericListScreen(null)
        }
    }
}