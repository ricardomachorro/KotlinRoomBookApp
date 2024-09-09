package com.example.bookorganizationapp.ui.main.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MainNavItem <T>(
    val drawerOption:T,
    @StringRes val title: Int,
    @DrawableRes val selectedIcon:Int,
    @StringRes val descriptionId:Int
)
