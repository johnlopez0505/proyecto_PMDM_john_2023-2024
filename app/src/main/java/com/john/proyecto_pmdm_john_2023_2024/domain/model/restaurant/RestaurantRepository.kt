package com.john.proyecto_pmdm_john_2023_2024.domain.model.restaurant

import android.content.ContentValues.TAG
import android.util.Log
import com.john.proyecto_pmdm_john_2023_2024.data.network.models.request.RequestInsertRestaurant
import com.john.proyecto_pmdm_john_2023_2024.data.network.service.restaurant.RestaurantApiService
import javax.inject.Inject



class RestaurantRepository @Inject constructor(
    private val restaurantService : RestaurantApiService
) {
    suspend fun getRestaurantList(token: String?) : List<Restaurant>?{
        Log.i(TAG, "getRestaurantList restaurante repository: ${restaurantService.getRestaurantList(token!!)}")
        val result = token?.let { restaurantService.getRestaurantList(it) }
        Log.i(TAG, "getRestaurantList restaurante repository : $result")
        val mutableRestaurant : MutableList<Restaurant> = mutableListOf()
        if (result != null) {
            result
                .onSuccess {
                        responseUser->
                    responseUser.restaurantes.forEach{ restaurant->
                        mutableRestaurant.add(Restaurant(
                            restaurant.nombre,
                            restaurant.ciudad,
                            restaurant.provincia,
                            restaurant.telefono,
                            restaurant.imagen!!,
                            restaurant.id,
                            restaurant.id_usuario!!))
                    }
                    Repository.restaurants = mutableRestaurant
                    return Repository.restaurants
                }
                .onFailure {
                        exception ->  println("Error en la excepcion ${exception.message}")
                }
        }
        return null
    }

    suspend fun addRestaurant(restaurant: Restaurant, token: String?): Restaurant? {
        val restaurantRequest = RequestInsertRestaurant(
            restaurant.id_usuario,restaurant.nombre,restaurant.ciudad,restaurant.provincia,
            restaurant.telefono,restaurant.imagen)
        Log.i(TAG, "addRestaurant restaurante request: $restaurantRequest")
        //Log.i(TAG, "addRestaurant: ${restaurantService.addRestaurant(restaurantRequest,token!!)}")
        try {
            val result = restaurantService.addRestaurant(restaurantRequest,token!!)
            Log.i(TAG, "addRestaurant result: $result")
            result
                .onSuccess {
                        responseRest ->
                    Log.i(TAG, "addRestaurant: ${responseRest.result} ${responseRest.insert_id}")
                    return Restaurant(
                        responseRest.result,
                        responseRest.insert_id,
                        responseRest.file_img
                    )
                }
                .onFailure {
                        exception ->  println("Error en la excepcion ${exception.message}")
                }
        }catch (e : Exception){
            println("error en los datos")
        }
        return null
    }

    suspend fun deleteRestaurant(id: Int, token: String?):Restaurant?{
        val result = restaurantService.deleteRestaurant(id,token!!)
        result
            .onSuccess {
                    responseRest ->
                return Restaurant(
                    responseRest.result
                )
            }
            .onFailure {
                    exception ->  println("Error en la excepcion ${exception.message}")
            }
        return null
    }

    suspend fun editRestaurant(
        id : Int, newRestaurant:
        Restaurant, token: String?): Restaurant? {
        Log.i(TAG, "editRestaurant id restaurante: $id")
        val newRestaurantRequest = RequestInsertRestaurant(
            newRestaurant.id_usuario,newRestaurant.nombre,newRestaurant.ciudad,
            newRestaurant.provincia,newRestaurant.telefono,newRestaurant.imagen)
        Log.i(TAG, "editRestaurant restaurante añadir: $newRestaurantRequest")
        val result =  restaurantService.editRestaurant(id,newRestaurantRequest,token!!)
        Log.i(TAG, "editRestaurant este es el resultado : $result")
        result
            .onSuccess {
                    responseRest ->
                return Restaurant(
                    responseRest.result
                )
            }
            .onFailure {
                    exception ->  println("Error en la excepcion ${exception.message}")
            }
        return  null
    }
}