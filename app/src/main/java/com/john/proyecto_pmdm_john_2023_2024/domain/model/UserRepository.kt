package com.john.proyecto_pmdm_john_2023_2024.domain.model


import android.content.ContentValues.TAG
import android.util.Log
import com.john.proyecto_pmdm_john_2023_2024.data.network.models.request.RequestLoginUser
import com.john.proyecto_pmdm_john_2023_2024.data.network.service.UserApiService
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: UserApiService
){

    suspend fun getUser(user : User) : User? {
        Log.i(TAG, "getUser: $user")
        val userRequest = RequestLoginUser(user.email, user.password!!)
        Log.i(TAG, "getUser userRequest: $userRequest")
        val result = apiService.getUser(userRequest)
        result
            .onSuccess {
                responseUser->
                    return User(
                        responseUser.id,
                        responseUser.token,
                        responseUser.email,
                        responseUser.password,
                        responseUser.disponible,
                        responseUser.image)

        }
            .onFailure {
                exception ->  println("Error en la excepcion ${exception.message}")
            }
        return null
    }
}