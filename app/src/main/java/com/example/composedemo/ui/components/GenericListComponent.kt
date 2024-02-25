package com.example.composedemo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.domain.mock.mockGenericListState
import com.example.composedemo.ui.states.GenericListState
import com.example.composedemo.ui.theme.ComposeDemoTheme

@Composable
fun GenericListComponent(
    listState: GenericListState,
    modifier: Modifier = Modifier,
    itemSpacing: Int = 4
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(itemSpacing.dp),
        modifier = modifier
    ) {
        items(listState.itemStates) { item ->
            GenericListItem(itemState = item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GenericListComponentPreview() {
    ComposeDemoTheme {
        GenericListComponent(mockGenericListState())
    }
}