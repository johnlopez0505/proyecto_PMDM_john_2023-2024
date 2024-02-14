package com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant

import com.john.proyecto_pmdm_john_2023_2024.data.service.RestaurantServiceInterface
import com.john.proyecto_pmdm_john_2023_2024.data.dataSource.Restaurants
import com.john.proyecto_pmdm_john_2023_2024.data.service.RestaurantService
import com.john.proyecto_pmdm_john_2023_2024.data.service.RestaurantService.Companion.service

class DaoRestaurant private constructor() : RestauratRepositoryInterfaceDao {
    private val service = RestaurantService
    private lateinit var mutableRestarant : MutableList<Restaurant>
    companion object {
        val myDao: DaoRestaurant by lazy {
            DaoRestaurant()
        }
    }

    override fun listRestaurant(): List<Restaurant> {

        val dataSource = service.service.listRestaurant()
        dataSource .forEach{ restaurant->
            mutableRestarant.add(Restaurant(
                restaurant.name,
                restaurant.city,
                restaurant.province,
                restaurant.phone,
                restaurant.image))
        }
        RepositoryRestaurant.restaurants = mutableRestarant //AQUÍ CARGO LOS DATOS EN MEMORIA.
        return RepositoryRestaurant.restaurants
    }

    override fun editRestaurant(pos: Int): Restaurant {
        val dataSource = service.service.editRestaurant(pos)
        mutableRestarant[pos] = dataSource
        RepositoryRestaurant.restaurants = mutableRestarant
        return dataSource
    }

    override fun addRestaurant(restaurant: Restaurant): Restaurant {
        // Agrega el restaurante a la lista y retorna la lista actual
        val dataSource = service.service.addRestaurant(restaurant)
        mutableRestarant.add(dataSource)
        RepositoryRestaurant.restaurants = mutableRestarant
        return dataSource
    }

    override fun deleteRestaurant(pos: Int): Restaurant {
        // Elimina el restaurante en la posición `pos` y retorna el restaurante eliminado
        val dataSource = service.service.deleteRestaurant(pos)
        mutableRestarant.remove(dataSource)
        RepositoryRestaurant.restaurants = mutableRestarant
        return dataSource
    }
}
