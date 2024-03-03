package com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant


import com.john.proyecto_pmdm_john_2023_2024.data.service.RestaurantService
import javax.inject.Inject

class DaoRestaurant @Inject constructor(private val service: RestaurantService) : RestauratRepositoryInterfaceDao {

    override fun listRestaurant(): List<Restaurant> {
        val mutableRestaurant : MutableList<Restaurant> = mutableListOf()
        val dataSource = service.listRestaurant()
        dataSource .forEach{ restaurant->
            mutableRestaurant.add(Restaurant(
                restaurant.name,
                restaurant.city,
                restaurant.province,
                restaurant.phone,
                restaurant.image))
        }
        RepositoryRestaurant.restaurants = mutableRestaurant
        return RepositoryRestaurant.restaurants
    }

    override fun editRestaurant(pos: Int,newRestaurant: Restaurant): List<Restaurant> {
        val mutableRestaurant : MutableList<Restaurant> =
            RepositoryRestaurant.restaurants.toMutableList()
        val dataSource = service.editRestaurant(pos)
        mutableRestaurant[dataSource] = newRestaurant
        RepositoryRestaurant.restaurants = mutableRestaurant
        return RepositoryRestaurant.restaurants
    }

    override fun addRestaurant(restaurant: Restaurant): List<Restaurant> {
        val mutableRestaurant : MutableList<Restaurant> =
            RepositoryRestaurant.restaurants.toMutableList()
        // Agrega el restaurante a la lista y retorna la lista actual
        val dataSource = service.addRestaurant(restaurant)
        mutableRestaurant.add(dataSource)
        RepositoryRestaurant.restaurants = mutableRestaurant
        return RepositoryRestaurant.restaurants
    }

    override fun deleteRestaurant(pos: Int): List<Restaurant> {
        val mutableRestaurant : MutableList<Restaurant> =
            RepositoryRestaurant.restaurants.toMutableList()
        // Elimina el restaurante en la posición `pos` y retorna el restaurante eliminado
        val dataSource = service.deleteRestaurant(pos)
        mutableRestaurant.removeAt(dataSource)
        RepositoryRestaurant.restaurants = mutableRestaurant
        return RepositoryRestaurant.restaurants
    }
}
