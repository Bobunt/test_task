package com.example.test_task.fragments

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainFragment() {
    val message = remember{ mutableStateOf("") }
    val viewModel: MainViewModel = hiltViewModel()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                content = {
                    item {
                        TextField(
                            modifier = Modifier.fillMaxSize(),
                            value = message.value,
                            onValueChange = { newText -> message.value = newText }
                        )
                    }
                    item {
                        Button(
                            modifier = Modifier.fillMaxSize(),
                            onClick = {
                                viewModel.addText(message.value)
                                message.value = ""
                            }) {
                            Text(text = "Добавить текст")
                        }
                    }
                    item {
                        Text(
                            modifier = Modifier.fillMaxSize(),
                            text  = viewModel.text
                        )
                    }
                })
        }
    }

}
