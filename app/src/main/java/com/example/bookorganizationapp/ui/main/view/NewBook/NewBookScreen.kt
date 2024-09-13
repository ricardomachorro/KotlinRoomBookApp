package com.example.bookorganizationapp.ui.main.view.NewBook

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookorganizationapp.R
import com.example.bookorganizationapp.data.internal.BookEvent
import com.example.bookorganizationapp.data.internal.BookState
import com.example.bookorganizationapp.ui.main.common.ScaffoldTemplate
import kotlinx.coroutines.CoroutineScope


@Composable
fun NewBookScreen(drawerState: DrawerState, scope: CoroutineScope, state: BookState, onEvent: (BookEvent) -> Unit){
   ScaffoldTemplate(drawerState,scope) {
       formNewBook(state= state,onEvent= onEvent)
   }
}


@Composable
fun formNewBook( state: BookState,onEvent: (BookEvent) -> Unit){
    

    Box(
        modifier = Modifier
            .fillMaxSize()

    ){

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){


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
                ){

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(17.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){

                        Text(
                            modifier = Modifier
                                .padding(end=10.dp),
                            text="Nuevo libro",
                            fontSize= 20.sp
                        )

                        Image(
                            modifier = Modifier
                                .size(40.dp),
                            painter = painterResource(id = R.drawable.book),
                            contentDescription = "book logo"
                        )

                    }
                }




            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ){


                Text(
                    modifier = Modifier
                        .padding( vertical =10.dp),
                    text="Titulo",
                    fontSize = 20.sp
                )

                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = state.name,
                    onValueChange = {
                      onEvent(BookEvent.SetTitle(it))
                    }
                )


                Text(
                    modifier = Modifier
                        .padding( vertical = 10.dp),
                    text ="Autor",
                    fontSize = 20.sp
                )

                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = state.author,
                    onValueChange = {
                        onEvent(BookEvent.SetAuthor(it))
                    }

                    )


                Text(
                    modifier = Modifier
                        .padding( vertical = 10.dp),
                    text ="Categoria",
                    fontSize = 20.sp
                )

                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = state.category,
                    onValueChange = {
                        onEvent(BookEvent.SetCategory(it))
                    })

            }



            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.Center
            ){
                Button(
                    onClick = {
                        onEvent(BookEvent.SaveBook)
                    }
                ){
                    Text(
                        text="Guardar"
                    )
                }
            }

        }


    }
}


