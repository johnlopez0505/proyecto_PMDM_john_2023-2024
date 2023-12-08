package com.john.proyecto_pmdm_john_2023_2024.interfaceDao

import com.john.proyecto_pmdm_john_2023_2024.models.Restaurant

interface InterfaceDao {
    fun getDataRestaurant(): List<Restaurant>
}