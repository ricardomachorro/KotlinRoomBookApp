package com.example.bookorganizationapp.data.internal

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bookorganizationapp.util.Converters


@Database(
    entities = [Book::class],
    version = 1
)

@TypeConverters(Converters::class)

abstract class AppRoomDatabase :RoomDatabase(){

    abstract val dao:BookDao


}