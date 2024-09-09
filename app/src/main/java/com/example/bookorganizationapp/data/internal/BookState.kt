package com.example.bookorganizationapp.data.internal

import java.util.Calendar

data class BookState(
    val id:Int =0,
    val books: List<Book> = emptyList(),
    val name: String = "",
    val author: String = "",
    val last_page_read: Int =0,
    val category: String ="",
    val first_read: Calendar = Calendar.getInstance(),
    val last_read: Calendar = Calendar.getInstance(),
    val finished: Boolean = false,
    val sortType: SortType = SortType.TITLE,
    val topBooks: List<Book> = emptyList(),

)
