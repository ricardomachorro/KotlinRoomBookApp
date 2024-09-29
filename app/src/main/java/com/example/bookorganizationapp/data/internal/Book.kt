package com.example.bookorganizationapp.data.internal

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar


@Entity()
data class Book(
    @PrimaryKey(autoGenerate = true)
    var id:Int =0,
    var name:String ="",
    var author: String ="",
    var category: String ="",
    var first_read: Calendar = Calendar.getInstance(),
    var last_read:Calendar = Calendar.getInstance(),
    var finished: Boolean = false,
    var last_page_read:Int =0
    )
