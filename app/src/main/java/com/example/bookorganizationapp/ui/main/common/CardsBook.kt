package com.example.bookorganizationapp.ui.main.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bookorganizationapp.data.internal.Book
import com.example.bookorganizationapp.data.internal.BookEvent
import com.example.bookorganizationapp.util.CalendarTools


@Composable
fun CardBook(navController: NavController,bookObject:Book,onEvent: (BookEvent) -> Unit){

    val calendarTool: CalendarTools= CalendarTools()

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 5.dp),
       /* colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )*/
    ){


       Column(
           modifier = Modifier
               .fillMaxWidth()
       ){

           Row(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(horizontal = 20.dp),
               horizontalArrangement = Arrangement.SpaceBetween,
               verticalAlignment = Alignment.CenterVertically
           ){
               Column {
                   Text(
                       text = bookObject.name,
                       fontSize = 25.sp,
                       fontWeight = FontWeight.ExtraBold
                   )

                   Text(
                       text = bookObject.author,
                       fontSize = 13.sp,
                       fontWeight = FontWeight.Light,
                       fontFamily = FontFamily.Monospace
                   )
               }

               Row(
                   verticalAlignment = Alignment.CenterVertically
               ){


                   IconButton(
                       onClick = {
                           onEvent(BookEvent.SetId(bookObject.id))
                           onEvent(BookEvent.SetTitle(bookObject.name))
                           onEvent(BookEvent.SetAuthor(bookObject.author))
                           onEvent(BookEvent.SetCategory(bookObject.category))
                           onEvent(BookEvent.SetPages(bookObject.last_page_read))
                           onEvent(BookEvent.SetStartedDate(bookObject.first_read))
                           onEvent(BookEvent.SetUpdatedDate(bookObject.last_read))
                           onEvent(BookEvent.SetBookCompleted(bookObject.finished))
                           navController.navigate("BookModification")

                       }) {
                       Icon(imageVector = Icons.Default.Edit,contentDescription = "")
                   }


                   IconButton(
                       onClick = {
                           onEvent(BookEvent.DeleteBook(bookObject))
                       }) {
                       Icon(imageVector = Icons.Default.Delete,contentDescription = "")
                   }

                   Checkbox(
                       checked = (bookObject.finished == true),
                       onCheckedChange = {
                           onEvent(BookEvent.SetId(bookObject.id))
                           onEvent(BookEvent.SetTitle(bookObject.name))
                           onEvent(BookEvent.SetAuthor(bookObject.author))
                           onEvent(BookEvent.SetCategory(bookObject.category))
                           onEvent(BookEvent.SetPages(bookObject.last_page_read))
                           onEvent(BookEvent.SetStartedDate(bookObject.first_read))
                           onEvent(BookEvent.SetUpdatedDate(bookObject.last_read))
                           onEvent(BookEvent.SetBookCompleted(!bookObject.finished))
                           onEvent(BookEvent.SaveBook)
                       }
                   )

               }
           }

           Row(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(horizontal =20.dp,vertical = 5.dp),
               horizontalArrangement = Arrangement.SpaceAround
           ){

               Text(
                   text="F.Actual:"+calendarTool.CalendarToString(bookObject.last_read)
               )



               Text(
                   text="F.Inicio:"+calendarTool.CalendarToString(bookObject.first_read)
               )



           }

           Row(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(horizontal =20.dp,vertical = 5.dp),
               horizontalArrangement = Arrangement.SpaceAround
           ){

               Text(
                   text="Categoria:"+ bookObject.category
               )



               Text(
                   text="Pagina:"+bookObject.last_page_read.toString()
               )



           }



       }

        /*

      Column(
           modifier = Modifier
               .fillMaxWidth()
               .padding(15.dp)
       ){



           Row(
               modifier = Modifier
                   .fillMaxWidth(),
               horizontalArrangement = Arrangement.SpaceBetween,


           ){



               Text(
                   buildAnnotatedString {
                       withStyle(
                           style = SpanStyle(
                               fontSize = 25.sp,
                               fontWeight = FontWeight.ExtraBold
                           )
                       ) {
                           append(bookObject.name)
                       }

                       withStyle(
                           style = SpanStyle(
                               fontSize =  15.sp,
                               fontWeight = FontWeight.Light
                           )
                       ){
                           append(" - "+bookObject.author)
                       }
                   }
               )

               Row(){
                   Column(){
                       Text(
                           "Finalizdo"
                       )
                       Checkbox(
                           checked = (bookObject.finished == true),
                           onCheckedChange = {
                               onEvent(BookEvent.SetId(bookObject.id))
                               onEvent(BookEvent.SetTitle(bookObject.name))
                               onEvent(BookEvent.SetAuthor(bookObject.author))
                               onEvent(BookEvent.SetCategory(bookObject.category))
                               onEvent(BookEvent.SetPages(bookObject.last_page_read))
                               onEvent(BookEvent.SetStartedDate(bookObject.first_read))
                               onEvent(BookEvent.SetUpdatedDate(bookObject.last_read))
                               onEvent(BookEvent.SetBookCompleted(!bookObject.finished))
                               onEvent(BookEvent.SaveBook)
                           }
                       )
                   }
               }




           }

          Row(
              modifier = Modifier
                  .fillMaxWidth(),
              horizontalArrangement = Arrangement.Start

          ){

              Text(bookObject.category)
          }



           Row(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(vertical = 10.dp),
               horizontalArrangement = Arrangement.SpaceBetween
           ){


               Column(){


                   Text(
                       text="F.Actual:"+calendarTool.CalendarToString(bookObject.last_read)
                   )



                   Text(
                       text="F.Inicio:"+calendarTool.CalendarToString(bookObject.first_read)
                   )

               }



               Column(){


                   Text(
                       text="Pagina:"+bookObject.last_page_read.toString()
                   )

                   Text(
                       text = if(bookObject.finished)  "finalizado" else "no finalizado"
                   )

               }

               Column(){

                   IconButton(
                       onClick = {
                           onEvent(BookEvent.SetId(bookObject.id))
                           onEvent(BookEvent.SetTitle(bookObject.name))
                           onEvent(BookEvent.SetAuthor(bookObject.author))
                           onEvent(BookEvent.SetCategory(bookObject.category))
                           onEvent(BookEvent.SetPages(bookObject.last_page_read))
                           onEvent(BookEvent.SetStartedDate(bookObject.first_read))
                           onEvent(BookEvent.SetUpdatedDate(bookObject.last_read))
                           onEvent(BookEvent.SetBookCompleted(bookObject.finished))
                           navController.navigate("BookModification")

                       }) {
                       Icon(imageVector = Icons.Default.Edit,contentDescription = "")
                   }

                   
                   IconButton(
                       onClick = {
                           onEvent(BookEvent.DeleteBook(bookObject))
                       }) {
                       Icon(imageVector = Icons.Default.Delete,contentDescription = "")
                   }

                  
                   
                   
               }



           }



       }

        */
    }

}