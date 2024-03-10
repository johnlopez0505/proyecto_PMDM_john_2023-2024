package com.john.proyecto_pmdm_john_2023_2024.domain.useCase.useCaseRestaurant

import com.john.proyecto_pmdm_john_2023_2024.domain.model.restaurant.Restaurant
import com.john.proyecto_pmdm_john_2023_2024.domain.model.restaurant.RestaurantRepository

import javax.inject.Inject

class ListRestaurantUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) {
    private var token: String? = null

    fun setToken(token :String){
        this.token = token
    }
    suspend operator fun invoke(tokenUser: String): List<Restaurant>? {
        return  restaurantRepository.getRestaurantList(tokenUser)
    }
}