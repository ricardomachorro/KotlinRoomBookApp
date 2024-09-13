package com.example.bookorganizationapp.ui.main.view.EditBook

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookorganizationapp.R
import com.example.bookorganizationapp.data.internal.BookEvent
import com.example.bookorganizationapp.data.internal.BookState
import com.example.bookorganizationapp.ui.main.common.ScaffoldTemplate
import com.example.bookorganizationapp.util.CalendarTools
import kotlinx.coroutines.CoroutineScope


@Composable
fun EditBookScreen(drawerState: DrawerState, scope: CoroutineScope, state: BookState, onEvent: (BookEvent) -> Unit){
    ScaffoldTemplate(drawerState,scope) {
        FormEditBook(state =state,onEvent =onEvent)
    }
}


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormEditBook(state: BookState,onEvent: (BookEvent) -> Unit){

    val openDialogDate = remember { mutableStateOf(false) }



    var updatedDateText by remember { mutableStateOf("Seleeccione la fecha") }

    val tool = CalendarTools();

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

                ElevatedCard (
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
                            text="Modificar libro",
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
                        .padding( vertical = 10.dp),
                    text ="Titulo",
                    fontSize = 20.sp
                )

                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = state.name,
                    onValueChange = {
                        onEvent(BookEvent.SetTitle(it))
                    },
                    label = { Text("Titulo del libro") }
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
                    value =state.author,
                    onValueChange = {
                        onEvent(BookEvent.SetAuthor(it))
                    },
                    label = { Text("Nombre del autor") }
                )

                Text(
                    modifier = Modifier
                        .padding( vertical = 10.dp),
                    text ="Pagina",
                    fontSize = 20.sp
                )

                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = state.last_page_read.toString(),
                    onValueChange = {
                        if (!it.isBlank()){
                            var finalValue =""
                            if (it.startsWith("0")){
                                finalValue = it.substring(1,it.length)
                            }else{
                                finalValue = it
                            }
                            onEvent(BookEvent.SetPages(finalValue.toInt()))
                        }else{
                            onEvent(BookEvent.SetPages(0))
                        }
                    },
                    label = { Text("Paginas") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

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
                    },
                    label = { Text("Categoria del libro") },
                    )


                    Text(
                        modifier = Modifier
                            .padding( vertical = 10.dp),
                        text ="Fecha ultima lectura",
                        fontSize = 20.sp
                    )

                    OutlinedButton(
                        modifier = Modifier.
                        fillMaxWidth(),
                        onClick = {
                            openDialogDate.value = true
                        }
                    ) {
                        Text(text = updatedDateText)

                    }

                    Text(
                        modifier = Modifier
                            .padding( vertical = 10.dp),
                        text ="Ultima fecha lectura actual:${tool.CalendarToString(state.last_read)}",
                        fontSize = 10.sp
                    )



                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ){
                        Button(
                            modifier = Modifier
                                .padding(vertical = 20.dp),
                            onClick = {
                                onEvent(BookEvent.SaveBook)
                                updatedDateText = "Seleeccione la fecha"
                            }
                        ){
                            Text(
                                text = "Guardar"
                            )
                        }
                    }


                if(openDialogDate.value){

                    val datePickerState = rememberDatePickerState()
                    val confirmEnabled = derivedStateOf { datePickerState.selectedDateMillis!= null }


                    DatePickerDialog(
                        onDismissRequest = { openDialogDate.value = false },
                        confirmButton = {
                            TextButton(onClick = {
                                openDialogDate.value = false
                                var date =  "No fecha seleccionada"
                                if(datePickerState.selectedDateMillis != null){

                                    date = tool.LongToStringDate(datePickerState.selectedDateMillis!!);
                                }
                                updatedDateText = date
                                onEvent(BookEvent.SetUpdatedDate(tool.StringToCalendar(date)))

                            },
                                enabled = confirmEnabled.value
                            ) {

                                Text("Ok")
                            }

                        }) {
                        DatePicker(
                            state = datePickerState
                        )

                    }





                }




            }

        }
    }


}