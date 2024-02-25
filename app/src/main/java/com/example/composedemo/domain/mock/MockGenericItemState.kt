package com.example.composedemo.domain.mock

import com.example.composedemo.ui.states.GenericItemState

fun mockGenericItemState(): GenericItemState {
    return GenericItemState(
        title = "Title 1",
        message = "Message 1",
    )
}