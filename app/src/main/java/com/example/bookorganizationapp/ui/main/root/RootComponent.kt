package com.example.bookorganizationapp.ui.main.root

import androidx.compose.runtime.Composable
import com.example.bookorganizationapp.data.internal.BookEvent
import com.example.bookorganizationapp.data.internal.BookState
import com.example.bookorganizationapp.data.internal.LibraryState
import com.example.bookorganizationapp.ui.main.navigation.MainScreen
//import com.example.bookorganizationapp.ui.main.navigation.NavDrawerComponent


@Composable
fun RootComponet(state: BookState,libraryState: LibraryState, onEvent:(BookEvent) -> Unit){
    //NavDrawerComponent(state,libraryState,onEvent)
    MainScreen(state = state, libraryState = libraryState, onEvent = onEvent )
}