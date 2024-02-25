package com.example.composedemo.domain.mock

import com.example.composedemo.ui.states.GenericItemState
import com.example.composedemo.ui.states.GenericListState

fun mockGenericListState(): GenericListState {
    return GenericListState(
        itemStates = listOf(
            GenericItemState("Title 1", "Message 1"),
            GenericItemState("Title 2", "Message 2")
        )
    )
}