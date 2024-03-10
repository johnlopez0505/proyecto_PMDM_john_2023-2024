package com.john.proyecto_pmdm_john_2023_2024.domain.useCase.useCaseUser


import android.content.ContentValues.TAG
import android.util.Log
import com.john.proyecto_pmdm_john_2023_2024.domain.model.user.User
import com.john.proyecto_pmdm_john_2023_2024.domain.model.UserRepository
import javax.inject.Inject

class LoginUserCase  @Inject constructor(
    private val userRepository: UserRepository
){
    private lateinit var posibleUser : User
    fun setUser(_posibleUser : User){
        posibleUser = _posibleUser
    }

    suspend operator fun invoke(): User?{
        Log.i(TAG, "invoke caso de uso : $posibleUser")
        return (userRepository.getUser(posibleUser))
    }
}