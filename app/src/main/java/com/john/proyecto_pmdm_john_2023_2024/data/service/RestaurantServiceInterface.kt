package com.john.proyecto_pmdm_john_2023_2024.data.service

import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.Restaurant

interface RestaurantServiceInterface {
    fun listRestaurant(): List<Restaurant>
    fun editRestaurant(pos : Int): Restaurant
    fun addRestaurant(restaurant: Restaurant): Restaurant
    fun deleteRestaurant(pos : Int): Restaurant

}