package com.john.proyecto_pmdm_john_2023_2024.ui.view.login

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.john.proyecto_pmdm_john_2023_2024.domain.model.User
import com.john.proyecto_pmdm_john_2023_2024.domain.useCase.useCaseUser.LoginUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.internal.Provider
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserCase: LoginUserCase
) : ViewModel() {
    var loginLiveData = MutableLiveData<User> ()


    fun isLogin(email: String, password: String) {
        viewModelScope.launch {
            Log.i(TAG, "isLogin: $email $password")
            val useCaseLogin =  loginUserCase
            val user = User(email ,password)
            Log.i(TAG, "isLogin usuario creado: $user")
            useCaseLogin.setUser(user)
            val usuario : User? = useCaseLogin()
            Log.i(TAG, "isLogin usuario devuelto del api: $usuario")
            usuario.let { loginLiveData.value = it
            }
        }
    }


}