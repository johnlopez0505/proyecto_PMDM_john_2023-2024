package com.john.proyecto_pmdm_john_2023_2024.ui.modelView.restaurante

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.Restaurant
import com.john.proyecto_pmdm_john_2023_2024.domain.useCase.useCaseRestaurant.CreateRestaurantUseCase
import com.john.proyecto_pmdm_john_2023_2024.domain.useCase.useCaseRestaurant.DeleteRestaurantUseCase
import com.john.proyecto_pmdm_john_2023_2024.domain.useCase.useCaseRestaurant.EditRestaurantUseCase
import com.john.proyecto_pmdm_john_2023_2024.domain.useCase.useCaseRestaurant.ListRestaurantUseCase
import com.john.proyecto_pmdm_john_2023_2024.ui.adapter.AdapterRestaurant
import com.john.proyecto_pmdm_john_2023_2024.ui.view.dialog.DialogDeleteRestaurant
import com.john.proyecto_pmdm_john_2023_2024.ui.view.dialog.DialogEditRestaurant
import com.john.proyecto_pmdm_john_2023_2024.ui.view.dialog.DialogNewRestaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val listRestaurantUseCase: ListRestaurantUseCase,
    private var editRestaurantUseCase: EditRestaurantUseCase,
    private var addRestaurantUseCase: CreateRestaurantUseCase,
    private var deleteRestaurantUseCase: DeleteRestaurantUseCase
    ): ViewModel() {


    var restaurantListLiveData = MutableLiveData<List<Restaurant>>() //repositorio observable.
    private var listRestaurantes: MutableList<Restaurant> = mutableListOf() // lista de objetos
    var progressBarLiveData = MutableLiveData<Boolean>() //progressbar observable

    init {
        listarRestarurants()
    }

    fun iniciar(adapterRestaurant: AdapterRestaurant) {
        listRestaurantes = adapterRestaurant.restaurantRepository.toMutableList()
        Log.i(TAG, "iniciar: $listRestaurantes")
    }

    private fun listarRestarurants() {
        viewModelScope.launch {
            progressBarLiveData.value = true //LiveData notifica del cambio.
            delay(1000)
            if (listRestaurantes.size == 0) {
                val data = listRestaurantUseCase()
                data.let {
                    //Invocamos a nuestro caso de uso (lógica de negocio).
                    restaurantListLiveData.value = it
                    progressBarLiveData.value = false //LiveData notifica del cambio.
                }
            } else {
                Log.i(TAG, "listarRestarurants: estoy en el else")
                restaurantListLiveData.value = listRestaurantes
                progressBarLiveData.value = false //LiveData notifica del cambio.
            }
        }
    }



    fun addRestaurant(recyclerView: RecyclerView, context: FragmentActivity) {
        DialogNewRestaurant()
            .mostrarDialogoNewRestaurant(
                recyclerView,context,addRestaurantUseCase,restaurantListLiveData
        )
    }

    fun delRestaurant(pos: Int, recyclerView: RecyclerView, context: Context) {
        DialogDeleteRestaurant()
            .mostrarDialogoEliminarRestaurante(
                pos, recyclerView, context, deleteRestaurantUseCase, restaurantListLiveData
        )
    }

    fun updateRestaurant(pos: Int, recyclerView: RecyclerView, context: Context) {
        DialogEditRestaurant()
            .mostrarDialogoEditarRestaurante(
            pos, recyclerView, context,editRestaurantUseCase,restaurantListLiveData
        )
    }

}