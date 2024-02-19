package com.john.proyecto_pmdm_john_2023_2024.domain.useCase

import android.content.ContentValues.TAG
import android.util.Log
import com.google.android.material.animation.Positioning
import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.DaoRestaurant
import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.Restaurant
import javax.inject.Inject


class EditRestaurantUseCase @Inject constructor(private val daoRestaurant : DaoRestaurant){

    private var restaurant: Restaurant? = null
    private var pos: Int = 0

    fun setRestaurant(pos: Int,restaurant: Restaurant){
        this.pos = pos
        this.restaurant = restaurant
    }

    operator fun invoke(): List<Restaurant> {
        return  daoRestaurant.editRestaurant(pos,restaurant!!)
    }
}