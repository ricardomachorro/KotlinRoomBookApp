package com.example.bookorganizationapp.data.internal

import java.util.Calendar

sealed interface BookEvent {

    object SaveBook: BookEvent
    data class SetId(val id:Int):BookEvent
    data class SetTitle(val titleBook:String):BookEvent
    data class SetAuthor(val authorBook:String):BookEvent
    data class SetPages(val pagesBook:Int):BookEvent
    data class SetCategory(val categoryBook:String):BookEvent
    data class SetUpdatedDate(val dateUpdatedBook: Calendar):BookEvent
    data class SetStartedDate(val dateStartedBook: Calendar):BookEvent
    data class SetBookCompleted(val completedBook: Boolean) : BookEvent
    data class SortBooks(val sorType:SortType):BookEvent
    data class DeleteBook(val book:Book):BookEvent


}