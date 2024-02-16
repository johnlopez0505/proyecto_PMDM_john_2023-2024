package com.john.proyecto_pmdm_john_2023_2024.domain.useCase

import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.DaoRestaurant
import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.Restaurant


class EditRestaurantUseCase (){
    private val daoRestaurant = DaoRestaurant.myDao

    operator fun invoke(pos: Int): List<Restaurant> {
        return  daoRestaurant.editRestaurant(pos)
    }
}