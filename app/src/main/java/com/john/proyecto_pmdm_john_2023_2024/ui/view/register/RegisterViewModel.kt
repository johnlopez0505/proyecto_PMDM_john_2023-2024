package com.john.proyecto_pmdm_john_2023_2024.ui.view.register

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.john.proyecto_pmdm_john_2023_2024.domain.model.RegisterUser
import com.john.proyecto_pmdm_john_2023_2024.domain.useCase.useCaseUser.RegisterUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserCase: RegisterUserCase
) : ViewModel(){
    var registerLiveData = MutableLiveData<String?> ()

    fun isRegister(nombre: String,email: String, password: String) {
        viewModelScope.launch {
            val useCaseRegister =  registerUserCase
            val user = RegisterUser(email,password,nombre,"1",null)
            useCaseRegister.setUser(user)
            val idUsuario : String? = useCaseRegister()
            registerLiveData.value = idUsuario

        }
    }

}