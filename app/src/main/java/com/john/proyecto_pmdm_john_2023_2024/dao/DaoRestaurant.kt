package com.john.proyecto_pmdm_john_2023_2024.dao

import com.john.proyecto_pmdm_john_2023_2024.interfaceDao.InterfaceDao
import com.john.proyecto_pmdm_john_2023_2024.models.Restaurant
import com.john.recicleview.object_model.Repository

class DaoRestaurant private constructor(): InterfaceDao {
    companion object {
        val myDao: DaoRestaurant by lazy{ //lazy delega a un primer acceso
            DaoRestaurant() //Me creo sólo este objeto una vez.
        }
    }
    //Método que accede a la BBDD y devuelve todos los datos
    override
    fun getDataRestaurant(): List<Restaurant> = Repository. listRestauran
}

object DaoHotels2 {
    val myDao by lazy {
        getDataRestaurant()
    }
    private fun getDataRestaurant() : List<Restaurant> = Repository. listRestauran
}