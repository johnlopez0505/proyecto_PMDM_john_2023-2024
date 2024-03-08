package com.john.proyecto_pmdm_john_2023_2024.domain.model.restaurant

import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.Restaurant
import com.john.proyecto_pmdm_john_2023_2024.data.network.models.request.RequestRestaurantList
import com.john.proyecto_pmdm_john_2023_2024.data.network.service.restaurant.RestaurantApiService
import com.john.proyecto_pmdm_john_2023_2024.domain.model.user.User
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val restauranteService : RestaurantApiService
) {
    suspend fun getRestaurantList(user: User) : List<Restaurant>?{
        val restaurantReques = RequestRestaurantList(user.token!!)
        val result = restauranteService.getRestaurantList(restaurantReques)
        result
            .onSuccess {
                    responseUser->
                return responseUser.restaurantes
            }
            .onFailure {
                    exception ->  println("Error en la excepcion ${exception.message}")
            }
        return null
    }
}