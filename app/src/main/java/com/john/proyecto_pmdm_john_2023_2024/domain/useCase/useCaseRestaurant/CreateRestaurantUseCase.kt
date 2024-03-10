package com.john.proyecto_pmdm_john_2023_2024.domain.useCase.useCaseRestaurant

import com.john.proyecto_pmdm_john_2023_2024.domain.model.restaurant.Restaurant
import com.john.proyecto_pmdm_john_2023_2024.domain.model.restaurant.RestaurantRepository
import javax.inject.Inject

class CreateRestaurantUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) {
    private var restaurant: Restaurant? = null
    private var token: String? = null

    fun setNewRestaurant(restaurant: Restaurant, token: String?){
        this.restaurant = restaurant
        this.token
    }
    suspend operator fun invoke(newRestaurant: Restaurant, token: String?): Restaurant? {
        return  restaurantRepository.addRestaurant(newRestaurant, token)
    }
}