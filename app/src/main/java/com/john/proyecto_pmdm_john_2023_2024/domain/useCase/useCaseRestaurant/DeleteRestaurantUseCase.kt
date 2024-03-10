package com.john.proyecto_pmdm_john_2023_2024.domain.useCase.useCaseRestaurant

import com.john.proyecto_pmdm_john_2023_2024.domain.model.restaurant.Restaurant
import com.john.proyecto_pmdm_john_2023_2024.domain.model.restaurant.RestaurantRepository
import javax.inject.Inject

class DeleteRestaurantUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository
){
    private var restaurant: Restaurant? = null
    private var token: String? = null

    fun setDeleteRestaurant(restaurant: Restaurant,token: String){
        this.restaurant = restaurant
        this.token = token

    }
    suspend operator fun invoke(id: Int,token: String?): Restaurant? {
        return  restaurantRepository.deleteRestaurant(id,token)
    }
}