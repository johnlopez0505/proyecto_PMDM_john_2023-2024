package com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant

interface RestauratRepositoryInterfaceDao {
    fun getDataRestaurant(): List<Restaurant>
    fun getDeleteRestaurant(pos : Int): List<Restaurant>
    fun getEditRestaurant(pos : Int): List<Restaurant>
    fun getAddRestaurant(): List<Restaurant>
}