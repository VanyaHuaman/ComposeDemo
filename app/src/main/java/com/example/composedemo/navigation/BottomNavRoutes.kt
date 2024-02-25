package com.example.composedemo.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.composedemo.R

sealed class BottomNavRoutes(
    val route: String,
    @StringRes val label: Int,
    @DrawableRes val icon: Int
) {
    data object Home : BottomNavRoutes(
        "bottomNavHome",
        R.string.home,
        R.drawable.baseline_home_24
    )
    data object GenericListScreen : BottomNavRoutes(
        "bottomNavGenericListScreen",
        R.string.list,
        R.drawable.baseline_list_24
    )
}