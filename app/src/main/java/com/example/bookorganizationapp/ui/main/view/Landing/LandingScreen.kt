package com.example.bookorganizationapp.ui.main.view.Landing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookorganizationapp.data.internal.BookEvent
import com.example.bookorganizationapp.data.internal.BookState
import com.example.bookorganizationapp.data.internal.LibraryState
import com.example.bookorganizationapp.ui.main.common.CardBook
import com.example.bookorganizationapp.ui.main.common.ScaffoldTemplate
import kotlinx.coroutines.CoroutineScope


@Composable
fun LandingScreen(navController: NavController,
                 // drawerState: DrawerState, scope: CoroutineScope,
                  state: BookState,library:LibraryState, onEvent: (BookEvent) -> Unit){
   /* ScaffoldTemplate(drawerState,scope) {
       ResumeScreen(navController,state,library,onEvent)
    }*/
    ResumeScreen(navController,state,library,onEvent)
}



@Composable
fun ResumeScreen(navController: NavController,state: BookState,library: LibraryState,onEvent: (BookEvent) -> Unit){



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
            ) {
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
                            .fillMaxWidth()
                            .padding(17.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {

                        Column(
                            modifier = Modifier
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Total de libros",
                            )
                            Text(
                                text = library.total_books.toString(),
                            )
                        }

                        Column(
                            modifier = Modifier
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Libros no leidos",
                            )
                            Text(
                                text =  library.books_not_read.toString(),
                            )
                        }
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

                    items(state.topBooks) {

                            book ->

                        CardBook(navController = navController, bookObject = book, onEvent = onEvent)
                    }


                }

            }

        }

    }

}