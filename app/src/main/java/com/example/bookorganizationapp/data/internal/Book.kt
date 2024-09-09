package com.example.bookorganizationapp.data.internal

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar


@Entity()
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id:Int =0,
    val name:String,
    val author: String,
    val category: String,
    val first_read: Calendar = Calendar.getInstance(),
    val last_read:Calendar = Calendar.getInstance(),
    val finished: Boolean = false,
    val last_page_read:Int =0
    )
