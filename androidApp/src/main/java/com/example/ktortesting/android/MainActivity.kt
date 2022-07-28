package com.example.ktortesting.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ktortesting.Greeting
import com.example.ktortesting.android.screens.home.HomeViewModel
import com.example.ktortesting.domain.repository.UniversitiesRepositoryImpl
import com.example.ktortesting.ui.UniversityItemUI
import kotlinx.coroutines.flow.collectLatest

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = HomeViewModel(UniversitiesRepositoryImpl())
        setContent {

            LaunchedEffect(key1 = null, block = {
                viewModel.state.collectLatest {
                    Log.e("TAG", "Данные поменялись !!!")
                }
            })


            val state by viewModel.state.collectAsState()

            Box(modifier = Modifier.fillMaxSize()) {
                when {
                    state.isLoading -> {
                        Box(modifier = Modifier.align(Alignment.Center)) {
                            CircularProgressIndicator()
                        }
                    }
                    !state.isLoading -> {
                        LazyColumn(content = {
                            items(state.list) { universityItem ->
                                UniversityItemUI(universityItem)
                            }
                        })
                    }
                }
            }
        }
    }

    @Composable
    fun UniversityItemUI(item: UniversityItemUI) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = item.name, style = typography.h5)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = item.country)
            Divider()
        }
    }
}
