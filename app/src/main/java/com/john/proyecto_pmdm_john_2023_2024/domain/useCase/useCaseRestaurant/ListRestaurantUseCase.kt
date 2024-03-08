package com.john.proyecto_pmdm_john_2023_2024.domain.useCase.useCaseRestaurant

import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.Restaurant
import com.john.proyecto_pmdm_john_2023_2024.domain.model.UserRepository
import com.john.proyecto_pmdm_john_2023_2024.domain.model.restaurant.RestaurantRepository
import com.john.proyecto_pmdm_john_2023_2024.domain.model.user.RegisterUser
import com.john.proyecto_pmdm_john_2023_2024.domain.model.user.User
import javax.inject.Inject

class ListRestaurantUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) {
    private lateinit var posibleUser : User
    fun setUser(_posibleUser : User){
        posibleUser = _posibleUser
    }
    suspend operator fun invoke(): List<Restaurant>? {
        return  restaurantRepository.getRestaurantList(posibleUser)
    }
}