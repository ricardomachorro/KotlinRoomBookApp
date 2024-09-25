package com.example.bookorganizationapp.ui.main.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bookorganizationapp.R
import com.example.bookorganizationapp.data.internal.BookEvent
import com.example.bookorganizationapp.data.internal.BookState
import com.example.bookorganizationapp.data.internal.LibraryState
import com.example.bookorganizationapp.ui.main.navigation.NavigationHost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldTemplate(drawerState: DrawerState, scope: CoroutineScope, content: @Composable () -> Unit ){
    Scaffold(
        topBar =  {
            TopAppBar(
                title = {
                    Row() {
                        Text("Organization Book App")
                        Icon(
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .size(30.dp),
                            painter = painterResource(id = R.drawable.book),
                            contentDescription = "")

                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                drawerState.open()
                            }

                        }
                    ) {
                        Icon(Icons.Filled.Menu,"Icon menu")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) {

        innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),

        ) {
            content()
        }

    }

}


@Composable
fun NewScaffoldTemplate(scope:CoroutineScope, drawerState: DrawerState, navController:NavHostController,  state: BookState, library: LibraryState, onEvent: (BookEvent) -> Unit){

    Scaffold(
        topBar = {
            CostumeTopBar(scope = scope, drawerState = drawerState )
        }
    ) {

            innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),

            ) {
            NavigationHost(navController = navController,scope =scope,state=state,library =library,onEvent =onEvent)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CostumeTopBar(
    scope: CoroutineScope,
    drawerState: DrawerState
){

    TopAppBar(
        title = {Text("Organization App")},
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    drawerState.apply {
                        if (isClosed) open() else close()
                    }
                }
            }) {

                Icon(imageVector =  Icons.Filled.Menu,
                    contentDescription = "")
            }
        }
    )


}