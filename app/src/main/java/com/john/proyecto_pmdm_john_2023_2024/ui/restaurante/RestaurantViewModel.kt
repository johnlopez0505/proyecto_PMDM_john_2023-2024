package com.john.proyecto_pmdm_john_2023_2024.ui.restaurante

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.Restaurant
import com.john.proyecto_pmdm_john_2023_2024.domain.useCase.CreateRestaurantUseCase
import com.john.proyecto_pmdm_john_2023_2024.domain.useCase.DeleteRestaurantUseCase
import com.john.proyecto_pmdm_john_2023_2024.domain.useCase.EditRestaurantUseCase
import com.john.proyecto_pmdm_john_2023_2024.domain.useCase.ListRestaurantUseCase


class RestaurantViewModel : ViewModel() {
    var restaurantListLiveData = MutableLiveData<List<Restaurant>>() //repositorio observable.
    private val listRestaurantUseCase = ListRestaurantUseCase().invoke()
    private val editRestaurantUseCase = EditRestaurantUseCase().invoke()
    private val addRestaurantUseCase = CreateRestaurantUseCase().invoke()
    private val deleteRestaurantUseCase = DeleteRestaurantUseCase().invoke()


    fun list() {
            var data: List<Restaurant>? = listRestaurantUseCase //Invocamos a nuestro caso de uso (lógica de negocio).
            data.let {
                restaurantListLiveData.value = it //LiveData notifica del cambio.
            }

    }
    fun editarRestaurante(){

    }
}