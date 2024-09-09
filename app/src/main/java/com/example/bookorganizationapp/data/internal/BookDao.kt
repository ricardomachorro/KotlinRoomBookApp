package com.example.bookorganizationapp.data.internal

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface BookDao {


    @Upsert
    fun upsertBook(bookEntrance:Book)

    @Delete
    fun deleteBook(bookToDelate:Book)

    @Query("SELECT * FROM Book ORDER BY id ASC")
    fun getBooksOrderedById(): Flow<List<Book>>


    @Query("SELECT * FROM Book ORDER BY last_read ASC LIMIT 5")
    fun getTopFiveBooksOrderedByDate(): Flow<List<Book>>

    @Query("SELECT * FROM Book ORDER BY last_read")
    fun getBooksOrderedByUpdatedDate():Flow<List<Book>>

    @Query("SELECT * FROM Book ORDER BY name ASC")
    fun getBooksOrderedByName():Flow<List<Book>>

    @Query("SELECT * FROM Book ORDER BY author ASC")
    fun getBooksOrderedByAuthor():Flow<List<Book>>

    @Query("SELECT * FROM Book ORDER BY category ASC")
    fun getBooksOrderedByCategory():Flow<List<Book>>

    @Query("SELECT COUNT(id) FROM Book")
    fun getNumberTotalBooks():Flow<Int>

    @Query("SELECT COUNT(id) FROM Book WHERE finished=FALSE")
    fun getNumberTotalBooksNotRead():Flow<Int>


}