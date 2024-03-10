package com.john.proyecto_pmdm_john_2023_2024.domain.useCase.useCaseRestaurant

import com.john.proyecto_pmdm_john_2023_2024.domain.model.restaurant.Restaurant
import com.john.proyecto_pmdm_john_2023_2024.domain.model.restaurant.RestaurantRepository
import javax.inject.Inject

class EditRestaurantUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) {
    private var idRestaurant: Restaurant? = null
    private var newRestaurant : Restaurant? = null
    private var token: String? = null

    fun setEditRestaurant(idRestaurant: Restaurant, newRestaurant: Restaurant, token: String){
        this.idRestaurant = idRestaurant
        this.newRestaurant = newRestaurant
        this.token = token

    }
    suspend operator fun invoke(
        id: Int,
        editedRestaurant: Restaurant,
        token: String?
    ): Restaurant? {
        return  restaurantRepository.editRestaurant(id,editedRestaurant, token)
    }
}