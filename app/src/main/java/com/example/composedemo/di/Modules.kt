package com.example.composedemo.di

import com.example.composedemo.viewmodels.GenericListScreenViewModel
import org.koin.dsl.module

object Modules {
    val appModule = module {

    }

    val viewModelModule = module {
        single<GenericListScreenViewModel> { GenericListScreenViewModel() }
    }
}