package com.example.bookorganizationapp.ui.main.navigation

import android.annotation.SuppressLint
import android.view.Menu
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.bookorganizationapp.data.internal.BookEvent
import com.example.bookorganizationapp.data.internal.BookState
import com.example.bookorganizationapp.data.internal.LibraryState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun NavDrawerComponent(state: BookState, libraryState: LibraryState, onEvent: (BookEvent) -> Unit){


    //Remember Clicked index state
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

    //Drawer elements
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val scope = rememberCoroutineScope()

    val navHostController = rememberNavController()


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier
                    .height(16.dp))

                DrawerParams.drawerButtons.forEachIndexed{
                    index,item ->
                    NavigationDrawerItem(
                        icon = {
                               Icon(
                                   modifier = Modifier
                                       .size(20.dp),
                                  painter = painterResource(id = item.selectedIcon),
                                   contentDescription = "${stringResource(id = item.descriptionId)}"
                               )
                        },
                        label = { Text(text= stringResource(id = item.title))},
                        selected = index == selectedItemIndex,
                        onClick = {
                            selectedItemIndex = index
                            navHostController.navigate(item.drawerOption.name)
                            scope.launch {
                                drawerState.close()
                            }

                        }
                        
                    )
                }
            }

        }) {


        NavigationDrawerRoutes(navController = navHostController , drawerState = drawerState , scope = scope,state=state,library =libraryState,onEvent=onEvent)

    }
}







/*
@Composable
fun MainScreen(state: BookState, libraryState: LibraryState, onEvent: (BookEvent) -> Unit){

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    Scaffold (
        topBar = {CostumeTopBar(scope,drawerState)},


    ){
            innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),

            ) {

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
                    drawerState.open()
                }
            }) {

                Icon(imageVector =  Icons.Filled.Menu,
                    contentDescription = "")
            }
        }
    )
    
}
*/


@Composable
fun MainScreen(state: BookState, libraryState: LibraryState, onEvent: (BookEvent) -> Unit){

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,

        drawerContent = {
            DrawerContontent()
        }
    ){

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

            }
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

@Composable
fun DrawerContontent(){

    ModalDrawerSheet {


        Column(){
            DrawerParams.drawerButtons.forEach {
                    item ->
                TextButton(onClick = { /*TODO*/ }) {
                    Text(
                        modifier= Modifier.fillMaxWidth(),
                        text = stringResource(id = item.title))
                }
            }
        }
    }


}