package com.john.proyecto_pmdm_john_2023_2024.ui.modelView.restaurante

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.john.proyecto_pmdm_john_2023_2024.domain.model.restaurant.Restaurant
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
    private var editRestaurantUseCase:  EditRestaurantUseCase,
    private var addRestaurantUseCase: CreateRestaurantUseCase,
    private var deleteRestaurantUseCase: DeleteRestaurantUseCase
    ): ViewModel() {


    var restaurantListLiveData = MutableLiveData<List<Restaurant>>() //repositorio observable.
    private var listRestaurantes: MutableList<Restaurant> = mutableListOf() // lista de objetos
    var progressBarLiveData = MutableLiveData<Boolean>() //progressbar observable
    private  var tokenUser: String? = null

    init {
        listarRestarurants()
    }

    fun iniciar(adapterRestaurant: AdapterRestaurant, token: String?) {
        Log.i(TAG, "iniciar este es el token en iniciar: $token")
        if (token != null) {
            tokenUser = token
            Log.i(TAG, "iniciar este es el token en iniciar: $token")
        }
        listRestaurantes = adapterRestaurant.restaurantRepository.toMutableList()
        Log.i(TAG, "iniciar: $listRestaurantes")

    }

    fun listarRestarurants() {
        viewModelScope.launch {
            progressBarLiveData.value = true //LiveData notifica del cambio.
            delay(1000)
            val data : List<Restaurant>? = listRestaurantUseCase.invoke(tokenUser!!)
            data.let {
                //Invocamos a nuestro caso de uso (lógica de negocio).
                restaurantListLiveData.value = it
                progressBarLiveData.value = false //LiveData notifica del cambio.
            }
        }
    }



    fun addRestaurant(recyclerView: RecyclerView, context: FragmentActivity, token: String?, id: String?
    ) {
        viewModelScope.launch  {
            DialogNewRestaurant()
                .mostrarDialogoNewRestaurant (
                    recyclerView,context,addRestaurantUseCase,restaurantListLiveData,token,id
                )
        }

    }

    fun delRestaurant(pos: Int, recyclerView: RecyclerView, context: Context,token: String?) {
        DialogDeleteRestaurant()
            .mostrarDialogoEliminarRestaurante(
                pos, recyclerView, context, deleteRestaurantUseCase, restaurantListLiveData,token
        )
    }

    fun updateRestaurant(
        pos: Int,
        recyclerView: RecyclerView,
        context: Context,
        token: String?,
        id: String?
    ) {
        DialogEditRestaurant()
            .mostrarDialogoEditarRestaurante(
            pos, recyclerView, context,editRestaurantUseCase,restaurantListLiveData,token,id
        )
    }

}