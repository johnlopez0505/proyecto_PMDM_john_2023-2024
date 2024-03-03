package com.john.proyecto_pmdm_john_2023_2024.domain.model


import android.content.ContentValues.TAG
import android.util.Log
import com.john.proyecto_pmdm_john_2023_2024.data.network.models.request.RequestLoginUser
import com.john.proyecto_pmdm_john_2023_2024.data.network.models.request.RequestRegisterUser
import com.john.proyecto_pmdm_john_2023_2024.data.network.service.UserApiService
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: UserApiService
){

    suspend fun getUser(user : User) : User? {
        Log.i(TAG, "getUser: $user")
        val userRequest = RequestLoginUser(user.email!!, user.password!!)
        Log.i(TAG, "getUser userRequest: $userRequest")
        val result = apiService.getUser(userRequest)
        Log.i(TAG, "getUser respuesta de apiserver.getUser: $result ")
        result
            .onSuccess {
                responseUser->
                    return User(
                        responseUser.id,
                        responseUser.token,
                        responseUser.name,
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

    suspend fun getRegisterUser(user: RegisterUser) : String? {
        Log.i(TAG, "getRegisterUser usuario recibido: $user")
        val userRequest = RequestRegisterUser(user.email,user.password,user.nombre,user.disponible,user.imagen)
        Log.i(TAG, "getRegisterUser userRequest: $userRequest")
        val result = apiService.getRegisterUser(userRequest)
        Log.i(TAG, "getRegisterUser respuesta de servidor del registro: $result ")
        result
            .onSuccess {
                    responseUser->
                return responseUser.id
            }
            .onFailure {
                    exception ->  println("Error en la excepcion ${exception.message}")
            }
        return null
    }
}