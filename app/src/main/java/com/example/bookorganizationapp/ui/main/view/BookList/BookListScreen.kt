package com.example.bookorganizationapp.ui.main.view.BookList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookorganizationapp.data.internal.BookEvent
import com.example.bookorganizationapp.data.internal.BookState
import com.example.bookorganizationapp.data.internal.SortType
import com.example.bookorganizationapp.ui.main.common.CardBook
import com.example.bookorganizationapp.ui.main.common.ScaffoldTemplate
import kotlinx.coroutines.CoroutineScope


@Composable
fun BookListScren(navController: NavController,
                  //drawerState: DrawerState, scope: CoroutineScope,
                  state: BookState, onEvent: (BookEvent) -> Unit){
   /* ScaffoldTemplate(drawerState,scope) {
       ListElement(navController = navController,state = state,onEvent)
   }*/
    ListElement(navController = navController,state = state,onEvent)
}


@Composable
fun ListElement(navController: NavController,state: BookState,onEvent: (BookEvent) -> Unit){

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ){

                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )

                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ){
                        Text(
                            text = "Lista libros"
                        )
                    }


                }

            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    item{
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            verticalAlignment = Alignment.CenterVertically
                        ){

                            SortType.values().forEach { sortType ->

                                Row(
                                    modifier = Modifier
                                        .clickable {
                                            onEvent(BookEvent.SortBooks(sortType))
                                        },
                                    verticalAlignment = CenterVertically
                                ) {

                                    RadioButton(
                                        selected = (state.sortType == sortType),
                                        onClick = {
                                            onEvent(BookEvent.SortBooks(sortType))
                                        }
                                    )
                                    Text(text = sortType.name)

                                }
                            }

                        }
                    }

                    items(state.books){
                        book ->
                        
                        CardBook(navController=navController,bookObject = book,onEvent = onEvent)
                    }

                }


            }






        }



    }

}