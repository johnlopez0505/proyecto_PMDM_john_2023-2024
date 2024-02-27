package com.john.proyecto_pmdm_john_2023_2024.data.network.service

import android.content.ContentValues.TAG
import android.util.Log
import com.john.proyecto_pmdm_john_2023_2024.data.network.models.request.RequestLoginUser
import com.john.proyecto_pmdm_john_2023_2024.data.network.models.responses.ResponseLogin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class UserApiService @Inject constructor(
    private val apiService: UserApiServiceInterface
) {

    suspend fun getUser(user : RequestLoginUser) : Result<ResponseLogin> =
        withContext(Dispatchers.IO){
        try{
            Log.i(TAG, "getUser en user api: $user")
            val response : Response<ResponseLogin> = apiService.login(user)
            Log.i(TAG, "getUser respuesta del servidor : ${response.body()} ")
            if (response.isSuccessful){
                response.body()?.let{
                    retUser->
                    return@let Result.success(retUser)
                }?: return@withContext Result.failure(RuntimeException("Respuesta de usuarios nula"))
            }else{
                return@withContext Result.failure(RuntimeException("Error en la llamada y sin respuesta"))
            }
        }
        catch (e : Exception){
            return@withContext Result.failure(e)
        }
    }
}