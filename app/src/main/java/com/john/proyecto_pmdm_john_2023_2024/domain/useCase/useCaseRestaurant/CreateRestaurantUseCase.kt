package com.john.proyecto_pmdm_john_2023_2024.domain.useCase.useCaseRestaurant

import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.DaoRestaurant
import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.Restaurant
import javax.inject.Inject

class CreateRestaurantUseCase @Inject constructor(
    private val daoRestaurant : DaoRestaurant){

    private var restaurant: Restaurant? = null

    fun setRestaurant(restaurant: Restaurant){
        this.restaurant = restaurant
    }
    operator fun invoke(): List<Restaurant> {
        return  daoRestaurant.addRestaurant(restaurant!!)
    }
}
