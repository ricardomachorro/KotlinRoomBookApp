package com.example.bookorganizationapp.ui.main.navigation

import android.annotation.SuppressLint
import android.view.Menu
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bookorganizationapp.R
import com.example.bookorganizationapp.data.internal.BookEvent
import com.example.bookorganizationapp.data.internal.BookState
import com.example.bookorganizationapp.data.internal.LibraryState
import com.example.bookorganizationapp.ui.main.common.NewScaffoldTemplate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


/*
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
}*/







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


    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    /*val navigationItems =  listOf(
        DestinosNavigationDrawer.Pantalla1,
        DestinosNavigationDrawer.Pantalla2,
        DestinosNavigationDrawer.Pantalla3
    )*/

    val navigationItems = DrawerParams.drawerButtons
    ModalNavigationDrawer(
        drawerState = drawerState,

        drawerContent = {
            DrawerContent(scope = scope,drawerState = drawerState,navController = navController,menu_items = navigationItems)
        }
    ){

        NewScaffoldTemplate(scope=scope, drawerState = drawerState,navController=navController,state=state,library=libraryState,onEvent = onEvent)

    }


}





@Composable
fun DrawerItem(
    item: MainNavItem<MainNavOptions>,
    selected:Boolean,
    onItemClick:(MainNavItem<MainNavOptions>) -> Unit
    ){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(6.dp)
            .clip(RoundedCornerShape(12))
            .background(
                if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)
                else Color.Transparent
            )
            .padding(8.dp)
            .clickable { onItemClick(item) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(id = item.selectedIcon),
            contentDescription = ""
        )

        Spacer(modifier = Modifier
            .width(12.dp))


        Text(
            text= stringResource(id =item.title),
            style = MaterialTheme.typography.bodyMedium,
            color = if (selected) MaterialTheme.colorScheme.secondary
            else MaterialTheme.colorScheme.onBackground
        )
    }

}

@Composable
fun DrawerContent(
    scope: CoroutineScope,
    drawerState: DrawerState,
    navController: NavHostController,
    menu_items:ArrayList<MainNavItem<MainNavOptions>>
    //menu_items:List<DestinosNavigationDrawer>
    ){

    ModalDrawerSheet {


        Column(){

            Image(
                painterResource(id = R.drawable.dino_book_portrait),
                contentDescription = "",
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(15.dp))
            

           val currentRoute = currentRoute(navController)


            menu_items.forEach{
                item ->
                DrawerItem(
                    item = item,
                    selected = currentRoute == item.drawerOption.name){
                    navController.navigate(item.drawerOption.name){
                        launchSingleTop = true
                    }
                    scope.launch {
                        drawerState.close()
                    }
                }
            }
           /* menu_items.forEach {
                item ->
                DrawerItem(
                    item = item,
                    selected = currentRoute == item.ruta){
                    navController.navigate(item.ruta){
                        launchSingleTop = true
                    }
                    scope.launch {
                        drawerState.close()
                    }
                }
            }*/
        }
    }


}


@Composable
fun currentRoute(navController: NavHostController):String?{
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route

}