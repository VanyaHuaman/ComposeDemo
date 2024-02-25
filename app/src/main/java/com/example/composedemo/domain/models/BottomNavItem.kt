package com.example.composedemo.domain.models

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val label: Int,
    val route: String,
    val selectedImage: ImageVector,
    val unselectedImage: ImageVector,
)
