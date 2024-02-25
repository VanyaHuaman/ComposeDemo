package com.example.composedemo.domain.mock

import com.example.composedemo.ui.states.GenericListScreenState

fun mockGenericListScreenState(): GenericListScreenState {
    return GenericListScreenState(
        genericListState = mockGenericListState()
    )
}