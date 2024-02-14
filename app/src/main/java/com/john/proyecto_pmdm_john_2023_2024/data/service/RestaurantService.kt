package com.john.proyecto_pmdm_john_2023_2024.data.service

import com.john.proyecto_pmdm_john_2023_2024.data.dataSource.Restaurants
import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.RepositoryRestaurant
import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.Restaurant

class RestaurantService : RestaurantServiceInterface {
    companion object {
        val service: RestaurantService by lazy{ //lazy delega a un primer acceso
            RestaurantService() //Me creo sólo este objeto una vez.
        }
    }

    override fun listRestaurant(): List<Restaurant> {
        return Restaurants.listRestauran
    }

    override fun editRestaurant(pos: Int): Restaurant {
        return Restaurants.listRestauran[pos]
    }

    override fun addRestaurant(restaurant: Restaurant): Restaurant {
        // Agrega un restaurante a la lista en Repository
        RepositoryRestaurant.restaurants = RepositoryRestaurant.restaurants + restaurant
        return restaurant
    }

    override fun deleteRestaurant(pos: Int): Restaurant {
        // Elimina el restaurante en la posición especificada
        return Restaurants.listRestauran[pos]
    }
}