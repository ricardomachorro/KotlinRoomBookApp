package com.example.bookorganizationapp.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookorganizationapp.data.internal.Book
import com.example.bookorganizationapp.data.internal.BookDao
import com.example.bookorganizationapp.data.internal.BookEvent
import com.example.bookorganizationapp.data.internal.BookState
import com.example.bookorganizationapp.data.internal.LibraryState
import com.example.bookorganizationapp.data.internal.SortType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar

class BookViewModel(
    private val dao:BookDao
) :ViewModel() {

    private val _top_books =  dao.getTopFiveBooksOrderedByDate()

    private val number_books = dao.getNumberTotalBooks()

    private val booksNotRead = dao.getNumberTotalBooksNotRead();



    private  val _sortType = MutableStateFlow(SortType.CATEGORY)

    private val _books = _sortType.flatMapLatest {sortType ->
        when(sortType){

            SortType.TITLE -> dao.getBooksOrderedByName()
            SortType.AUTHOR -> dao.getBooksOrderedByAuthor()
            SortType.CATEGORY -> dao.getBooksOrderedByCategory()
            SortType.DATE -> dao.getBooksOrderedByUpdatedDate()
        }

    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(BookState())

    val state  =
        combine(_state, _sortType, _books, _top_books){ state,sortType, books, topBooks->
        state.copy(
            books = books,
            sortType = sortType,
            topBooks = topBooks,
        )

    }.stateIn(viewModelScope,SharingStarted.WhileSubscribed(5000), BookState())


    private val _libraryState = MutableStateFlow(LibraryState())

    val libraryState =
        combine(_libraryState,number_books,booksNotRead){ libraryState, number_books,booksNotRead->
            libraryState.copy(
                total_books = number_books,
                books_not_read = booksNotRead
            )
        }.stateIn(viewModelScope,SharingStarted.WhileSubscribed(),LibraryState())


    fun onEvent(event: BookEvent){
        when(event){

            BookEvent.SaveBook ->{

                val id = state.value.id
                val title = state.value.name
                val author = state.value.author
                val page = state.value.last_page_read
                val category = state.value.category
                val updated = state.value.last_read
                val start = state.value.first_read
                val finished = state.value.finished


                if( title.isBlank() || author.isBlank() ||  category.isBlank() ){
                    return
                }

                val book = Book(
                    id = id,
                    name = title,
                    author = author,
                    last_page_read = page,
                    category = category,
                    last_read = updated,
                    first_read = start,
                    finished = finished
                )


                viewModelScope.launch {
                    dao.upsertBook(book)
                }

                _state.update { it.copy(
                    id =0,
                    name = "",
                    author = "",
                    last_page_read = 0,
                    category = "",
                    last_read = Calendar.getInstance(),
                    first_read = Calendar.getInstance(),
                    finished = false
                ) }

            }

            is BookEvent.DeleteBook -> {

                viewModelScope.launch {
                    dao.deleteBook(event.book)
                }
            }

            is BookEvent.SetId ->{
                _state.update { it.copy(
                    id = event.id
                ) }
            }
            is BookEvent.SetAuthor -> {
                _state.update { it.copy(
                    author = event.authorBook
                ) }
            }
            is BookEvent.SetBookCompleted -> {
                _state.update { it.copy(
                    finished = event.completedBook
                ) }
            }
            is BookEvent.SetCategory -> {
                _state.update { it.copy(
                    category = event.categoryBook
                ) }
            }
            is BookEvent.SetPages -> {
                _state.update { it.copy(
                    last_page_read = event.pagesBook
                ) }
            }
            is BookEvent.SetStartedDate -> {
                _state.update { it.copy(
                    first_read = event.dateStartedBook
                ) }
            }
            is BookEvent.SetTitle -> {
                _state.update { it.copy(
                    name = event.titleBook
                ) }
            }
            is BookEvent.SetUpdatedDate -> {
                _state.update { it.copy(
                    last_read = event.dateUpdatedBook
                ) }
            }
            is BookEvent.SortBooks -> {
                _sortType.value = event.sorType
            }
        }
    }

}