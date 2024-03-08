package com.john.proyecto_pmdm_john_2023_2024.data.network.service.restaurant

import android.content.ContentValues
import android.util.Log
import com.john.proyecto_pmdm_john_2023_2024.data.network.models.request.RequestRestaurantList
import com.john.proyecto_pmdm_john_2023_2024.data.network.models.responses.ResponseRestaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class RestaurantApiService @Inject constructor(
    private val restaurantService : RestaurantServiceInterface
) {

    suspend fun getRestaurantList(user: RequestRestaurantList) : Result<ResponseRestaurant> =
        withContext(Dispatchers.IO){
            try {
                val response : Response<ResponseRestaurant> = restaurantService.listarRestaurant(user.token)
                Log.i(ContentValues.TAG, "geRegisterUser respuesta del servidor : ${response.body()} ")
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