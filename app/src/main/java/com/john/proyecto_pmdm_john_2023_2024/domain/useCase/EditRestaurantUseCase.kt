package com.john.proyecto_pmdm_john_2023_2024.domain.useCase

import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.DaoRestaurant
import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.Restaurant


class EditRestaurantUseCase {
    private val daoRestaurante = DaoRestaurant

    private var pos: Int = 0

    fun setPost(pos: Int){
        this.pos = pos
    }
    operator fun invoke(): Restaurant {
        return  daoRestaurante.myDao.editRestaurant(pos)
    }
}