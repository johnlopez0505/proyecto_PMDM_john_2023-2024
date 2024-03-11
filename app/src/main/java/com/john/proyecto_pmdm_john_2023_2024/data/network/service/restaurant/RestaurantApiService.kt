package com.john.proyecto_pmdm_john_2023_2024.data.network.service.restaurant

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import com.john.proyecto_pmdm_john_2023_2024.data.network.models.request.RequestIdRestaurant
import com.john.proyecto_pmdm_john_2023_2024.data.network.models.request.RequestInsertRestaurant
import com.john.proyecto_pmdm_john_2023_2024.data.network.models.responses.ResponseCreate
import com.john.proyecto_pmdm_john_2023_2024.data.network.models.responses.ResponseRestaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class RestaurantApiService @Inject constructor(
    private val restaurantService : RestaurantApiServiceInterface
) {

    suspend fun getRestaurantList(token: String) : Result<ResponseRestaurant> =
        withContext(Dispatchers.IO){
            try {
                Log.i(TAG, "getRestaurantList restaurante apiServe: ${restaurantService.listarRestaurant(token)}")
                val response : Response<ResponseRestaurant> = restaurantService.listarRestaurant(token)
                val error = response.body()
                Log.i(TAG, "getLista resaturante respuesta del servidor : ${response.body()} ")
                if (response.isSuccessful){
                    response.body()?.let{

                            retUser->
                        return@let Result.success(retUser)
                    }?: return@withContext Result.failure(RuntimeException("Respuesta de usuarios nula"))
                }else{
                    //Log.i(TAG, "getRestaurantList mensaje del servidor: ${response.body()}")
                    return@withContext Result.failure(RuntimeException("Error en la llamada y sin respuesta"))
                }
            }
            catch (e : Exception){
                return@withContext Result.failure(e)
            }
        }

    suspend fun addRestaurant(restaurant: RequestInsertRestaurant,token: String) : Result<ResponseCreate> =
        withContext(Dispatchers.IO){
            try {
                val response : Response<ResponseCreate> = restaurantService.createRestaurant(restaurant,token)
                Log.i(TAG, "geRegisterUser respuesta del servidor : ${response.body()} ")
                if (response.isSuccessful){
                    response.body()?.let{
                            retRestaurant->
                        return@let Result.success(retRestaurant)
                    }?: return@withContext Result.failure(RuntimeException("Respuesta de usuarios nula"))
                }else{
                    return@withContext Result.failure(RuntimeException("Error en la llamada y sin respuesta"))
                }
            }
            catch (e : Exception){
                return@withContext Result.failure(e)
            }
        }

    suspend fun deleteRestaurant(id : Int, token: String) : Result<ResponseRestaurant> =
        withContext(Dispatchers.IO){
            Log.i(TAG, "deleteRestaurant id: $id")
            Log.i(TAG, "deleteRestaurant token: $token")
            try {
                //val idRequest = RequestIdRestaurant(id)
                val response : Response<ResponseRestaurant> = restaurantService.deleteRestaurant(id,token)
                Log.i(ContentValues.TAG, "gedeleteRestaurant respuesta del servidor : ${response.body()} ")
                if (response.isSuccessful){
                    response.body()?.let{
                            retRestaurant->
                        return@let Result.success(retRestaurant)
                    }?: return@withContext Result.failure(RuntimeException("Respuesta de usuarios nula"))
                }else{
                    return@withContext Result.failure(RuntimeException("Error en la llamada y sin respuesta"))
                }
            }
            catch (e : Exception){
                return@withContext Result.failure(e)
            }
        }

    suspend fun editRestaurant(id: Int,newRestaurant :
                RequestInsertRestaurant,token: String) : Result<ResponseCreate> =
        withContext(Dispatchers.IO){
            try {
                Log.i(TAG, "editRestaurant:id del restaurante $id")
                val newRestaurantRequest = RequestInsertRestaurant(
                    newRestaurant.id_usuario,newRestaurant.nombre,newRestaurant.ciudad,
                    newRestaurant.provincia,newRestaurant.telefono,newRestaurant.imagen)
                val response : Response<ResponseCreate> = restaurantService.editRestaurant(
                    id,newRestaurantRequest,token)
                Log.i(TAG, "geRegisterUser respuesta del servidor : ${response.body()} ")
                if (response.isSuccessful){
                    response.body()?.let{
                            retRestaurant->
                        return@let Result.success(retRestaurant)
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