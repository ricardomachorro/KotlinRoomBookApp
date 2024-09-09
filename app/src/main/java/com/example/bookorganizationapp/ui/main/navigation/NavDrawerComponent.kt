package com.example.bookorganizationapp.ui.main.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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