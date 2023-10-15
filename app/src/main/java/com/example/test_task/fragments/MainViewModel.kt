package com.example.test_task.fragments

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    var text by mutableStateOf("")

    fun addText(newString : String) { this.text = stringFromJNI(newString, text) }

    private external fun stringFromJNI(newString: String, text: String): String

    companion object {
        init { System.loadLibrary("myapplication") }
    }
}
