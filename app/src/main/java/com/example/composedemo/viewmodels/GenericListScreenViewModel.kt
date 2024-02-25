package com.example.composedemo.viewmodels

import androidx.lifecycle.ViewModel
import com.example.composedemo.domain.mock.mockGenericListScreenState
import kotlinx.coroutines.flow.MutableStateFlow

class GenericListScreenViewModel : ViewModel() {
    val genericListScreenState = MutableStateFlow(mockGenericListScreenState())
}