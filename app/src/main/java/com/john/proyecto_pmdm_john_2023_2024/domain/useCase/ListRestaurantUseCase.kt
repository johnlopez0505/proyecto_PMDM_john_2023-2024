package com.john.proyecto_pmdm_john_2023_2024.domain.useCase

import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.DaoRestaurant
import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.Restaurant
import javax.inject.Inject

class ListRestaurantUseCase @Inject constructor(private val daoRestaurant : DaoRestaurant) {
    operator fun invoke(): List<Restaurant>? {
        return  daoRestaurant.listRestaurant()
    }
}