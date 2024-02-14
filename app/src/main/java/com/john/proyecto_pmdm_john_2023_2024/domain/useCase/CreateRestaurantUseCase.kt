package com.john.proyecto_pmdm_john_2023_2024.domain.useCase

import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.DaoRestaurant
import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.Restaurant

class CreateRestaurantUseCase {
    private val daoRestaurante = DaoRestaurant

    private var restaurant: Restaurant = Restaurant()

    fun setRestauran(restaurant: Restaurant){
        this.restaurant = restaurant
    }
    operator fun invoke(): Restaurant {
        return  daoRestaurante.myDao.addRestaurant(restaurant)
    }
}
