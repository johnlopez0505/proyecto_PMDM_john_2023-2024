package com.john.proyecto_pmdm_john_2023_2024.ui.restaurante

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.Restaurant
import com.john.proyecto_pmdm_john_2023_2024.domain.ListRestaurantUseCase


class RestaurantViewModel : ViewModel() {
    var restaurantListLiveData = MutableLiveData<List<Restaurant>>() //repositorio observable.
    private val useCaseList = ListRestaurantUseCase().invoke()


    fun list() {
            var data: List<Restaurant>? = useCaseList //Invocamos a nuestro caso de uso (lógica de negocio).
            data.let {
                restaurantListLiveData.value = it //LiveData notifica del cambio.
            }

    }
}