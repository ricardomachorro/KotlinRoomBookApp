package com.example.bookorganizationapp.ui.main.navigation

import com.example.bookorganizationapp.R

sealed class DestinosNavigationDrawer(
    val icon:Int,
    val title:String,
    val ruta:String
) {
    object Pantalla1:DestinosNavigationDrawer(R.drawable.book,"home","home")
    object Pantalla2:DestinosNavigationDrawer(R.drawable.list,"list books", "listBook")
    object Pantalla3:DestinosNavigationDrawer(R.drawable.addition,"new book","newBook")
}