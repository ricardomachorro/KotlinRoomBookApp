package com.example.bookorganizationapp.ui.main.navigation

import com.example.bookorganizationapp.R

object DrawerParams {

    val drawerButtons = arrayListOf(

        MainNavItem(
            MainNavOptions.LandingScreen,
            R.string.mainNavDrawerOptionHomeOption,
            R.drawable.home,
            R.string.mainNavDrawerOptionHomeOptionDescription

        ),
        MainNavItem(
            MainNavOptions.NewEntranceBook,
            R.string.mainNavDrawerOptionNewBookOption,
            R.drawable.addition,
            R.string.mainNavDrawerOptionNewBookOptionDescription
        ),
        MainNavItem(
            MainNavOptions.BookList,
            R.string.mainNavDrawerOptionBookListOption,
            R.drawable.list,
            R.string.mainNavDrawerOptionBookListOptionDescription
        )
    )
}