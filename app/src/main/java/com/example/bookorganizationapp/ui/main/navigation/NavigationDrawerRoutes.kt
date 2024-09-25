package com.example.bookorganizationapp.ui.main.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookorganizationapp.data.internal.BookEvent
import com.example.bookorganizationapp.data.internal.BookState
import com.example.bookorganizationapp.data.internal.LibraryState
import com.example.bookorganizationapp.ui.main.view.BookList.BookListScren
import com.example.bookorganizationapp.ui.main.view.EditBook.EditBookScreen
import com.example.bookorganizationapp.ui.main.view.Landing.LandingScreen
import com.example.bookorganizationapp.ui.main.view.NewBook.NewBookScreen
import kotlinx.coroutines.CoroutineScope


/*
@Composable
fun NavigationDrawerRoutes(navController: NavHostController, drawerState: DrawerState, scope: CoroutineScope, state: BookState, library:LibraryState,onEvent: (BookEvent) -> Unit){
    NavHost(navController = navController, startDestination = MainNavOptions.LandingScreen.name){

        composable(MainNavOptions.LandingScreen.name){
            LandingScreen(navController=navController,drawerState = drawerState, scope = scope,state=state, library = library, onEvent=onEvent)
        }

        composable(MainNavOptions.NewEntranceBook.name){
            NewBookScreen(drawerState = drawerState, scope = scope,state=state,onEvent=onEvent)
        }

        composable(MainNavOptions.BookList.name){
            BookListScren(navController=navController,drawerState = drawerState, scope = scope , state = state , onEvent=onEvent)

        }

        composable("BookModification"){
            EditBookScreen(drawerState = drawerState, scope = scope ,state=state, onEvent=onEvent)
        }
    }
}*/

@Composable
fun NavigationHost(navController: NavHostController,scope: CoroutineScope, state: BookState, library:LibraryState,onEvent: (BookEvent) -> Unit){
    NavHost(navController = navController, startDestination = MainNavOptions.LandingScreen.name) {

        composable(MainNavOptions.LandingScreen.name){
            LandingScreen(navController = navController, state = state, library =library,onEvent = onEvent )

        }

        composable(MainNavOptions.NewEntranceBook.name){
            NewBookScreen(state = state,onEvent = onEvent)
        }

        composable(MainNavOptions.BookList.name){
            BookListScren(navController = navController,  state = state,onEvent =onEvent)
        }

        composable("BookModification"){
            EditBookScreen(state =state, onEvent = onEvent)
        }


    }

}