package com.example.bookorganizationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.bookorganizationapp.data.internal.AppRoomDatabase
import com.example.bookorganizationapp.model.BookViewModel
import com.example.bookorganizationapp.ui.main.root.RootComponet
import com.example.bookorganizationapp.ui.theme.BookOrganizationAppTheme

class MainActivity : ComponentActivity() {


    private val db by lazy{
        Room.databaseBuilder(
            applicationContext,
            AppRoomDatabase::class.java,
            "books.db"
        )
            .allowMainThreadQueries()
            .build()
    }


    private val viewModel by viewModels<BookViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return  BookViewModel(db.dao) as T
                }
            }
        }
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state by viewModel.state.collectAsState()
            val library by viewModel.libraryState.collectAsState()
            RootComponet(state = state, libraryState =library,onEvent = viewModel::onEvent)
        }
    }
}

