package com.john.proyecto_pmdm_john_2023_2024.domain.useCase.useCaseUser

import android.content.ContentValues
import android.util.Log
import com.john.proyecto_pmdm_john_2023_2024.domain.model.RegisterUser
import com.john.proyecto_pmdm_john_2023_2024.domain.model.UserRepository
import javax.inject.Inject

class RegisterUserCase @Inject constructor(
    private val userRepository: UserRepository
) {

    private lateinit var posibleUser : RegisterUser
    fun setUser(_posibleUser : RegisterUser){
        posibleUser = _posibleUser
    }

    suspend operator fun invoke(): String? {
        Log.i(ContentValues.TAG, "invoke caso de uso : $posibleUser")
        return (userRepository.getRegisterUser (posibleUser))
    }
}