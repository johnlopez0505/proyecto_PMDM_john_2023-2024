package com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant

import com.john.proyecto_pmdm_john_2023_2024.data.service.RestaurantService

class DaoRestaurant private constructor() : RestauratRepositoryInterfaceDao {
    private val service :RestaurantService = RestaurantService.restaurantService
    private var mutableRestaurant : MutableList<Restaurant> = mutableListOf()
    companion object {
        val myDao: DaoRestaurant by lazy {
            DaoRestaurant()
        }
    }

    override fun listRestaurant(): List<Restaurant> {

        val dataSource = service.listRestaurant()
        dataSource .forEach{ restaurant->
            mutableRestaurant.add(Restaurant(
                restaurant.name,
                restaurant.city,
                restaurant.province,
                restaurant.phone,
                restaurant.image))
        }
        RepositoryRestaurant.restaurants = mutableRestaurant //AQUÍ CARGO LOS DATOS EN MEMORIA.
        return RepositoryRestaurant.restaurants
    }

    override fun editRestaurant(pos: Int): Restaurant {
        val dataSource = service.editRestaurant(pos)
        mutableRestaurant[pos] = dataSource
        RepositoryRestaurant.restaurants = mutableRestaurant
        return dataSource
    }

    override fun addRestaurant(restaurant: Restaurant): Restaurant {
        // Agrega el restaurante a la lista y retorna la lista actual
        val dataSource = service.addRestaurant(restaurant)
        mutableRestaurant.add(dataSource)
        RepositoryRestaurant.restaurants = mutableRestaurant
        return dataSource
    }

    override fun deleteRestaurant(pos: Int): List<Restaurant> {
        // Elimina el restaurante en la posición `pos` y retorna el restaurante eliminado
        val dataSource = service.deleteRestaurant(pos)
        mutableRestaurant.remove(dataSource)
        RepositoryRestaurant.restaurants = mutableRestaurant
        return RepositoryRestaurant.restaurants
    }
}
