package com.john.proyecto_pmdm_john_2023_2024.domain.useCase.useCaseRestaurant

import android.content.ContentValues.TAG
import android.util.Log
import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.DaoRestaurant
import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.Restaurant
import javax.inject.Inject

class DeleteRestaurantUseCase @Inject constructor( private val daoRestaurant : DaoRestaurant){

    private var pos: Int = 0

    fun setPosition(pos: Int){
        this.pos = pos
    }
    operator fun invoke(): List<Restaurant> {
        return  daoRestaurant.deleteRestaurant(pos)
    }
}
