package com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant

import com.john.proyecto_pmdm_john_2023_2024.data.service.InterfaceDao
import com.john.proyecto_pmdm_john_2023_2024.data.dataSource.Restaurants

class DaoRestaurant private constructor(): InterfaceDao {
    companion object {
        val myDao: DaoRestaurant by lazy{ //lazy delega a un primer acceso
            DaoRestaurant() //Me creo sólo este objeto una vez.
        }
    }
    //Método que accede a la BBDD y devuelve todos los datos
    override
    fun listRestaurant(): List<Restaurant> = Restaurants. listRestauran
}
