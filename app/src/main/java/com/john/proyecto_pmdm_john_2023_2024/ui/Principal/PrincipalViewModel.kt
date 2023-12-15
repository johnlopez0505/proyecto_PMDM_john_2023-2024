package com.john.proyecto_pmdm_john_2023_2024.ui.Principal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PrincipalViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is main Fragment"
    }
    val text: LiveData<String> = _text
}