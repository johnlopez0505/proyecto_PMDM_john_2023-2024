package com.john.proyecto_pmdm_john_2023_2024.data.service

import com.john.proyecto_pmdm_john_2023_2024.data.dataSource.Restaurants
import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.RepositoryRestaurant
import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.Restaurant
import javax.inject.Inject

class RestaurantService @Inject constructor() : RestaurantServiceInterface {

    override fun listRestaurant(): List<Restaurant> {
        return Restaurants.listRestauran
    }

    override fun editRestaurant(pos: Int): Int {
        return pos
    }

    override fun addRestaurant(restaurant: Restaurant): Restaurant {
        return restaurant
    }

    override fun deleteRestaurant(pos: Int): Int {
        return pos
    }
}