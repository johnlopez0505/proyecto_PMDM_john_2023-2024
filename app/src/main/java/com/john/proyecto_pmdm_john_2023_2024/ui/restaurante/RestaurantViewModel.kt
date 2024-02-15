package com.john.proyecto_pmdm_john_2023_2024.ui.restaurante

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.DaoRestaurant
import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.Restaurant
import com.john.proyecto_pmdm_john_2023_2024.domain.useCase.CreateRestaurantUseCase
import com.john.proyecto_pmdm_john_2023_2024.domain.useCase.DeleteRestaurantUseCase
import com.john.proyecto_pmdm_john_2023_2024.domain.useCase.EditRestaurantUseCase
import com.john.proyecto_pmdm_john_2023_2024.domain.useCase.ListRestaurantUseCase
import com.john.proyecto_pmdm_john_2023_2024.ui.adapter.AdapterRestaurant
import com.john.proyecto_pmdm_john_2023_2024.ui.view.DialogDeleteRestaurant
import com.john.proyecto_pmdm_john_2023_2024.ui.view.DialogEditRestaurant
import com.john.proyecto_pmdm_john_2023_2024.ui.view.DialogNewRestaurant
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class RestaurantViewModel : ViewModel(), DialogEditRestaurant.EditRestaurantDialogListener {
    var restaurantListLiveData = MutableLiveData<List<Restaurant>>() //repositorio observable.
    var deleteRestaurantLiveData = MutableLiveData<Restaurant>()
    var updateRestauranteLiveData = MutableLiveData<Restaurant>()
    var listRestaurants: MutableList<Restaurant> = mutableListOf() // lista de objetos
    var progressBarLiveData = MutableLiveData<Boolean> () //progressbar observable
    var buttonAddRestauran = MutableLiveData<Restaurant>()
    private  var listRestaurantUseCase   : ListRestaurantUseCase   = ListRestaurantUseCase()
    private  var editRestaurantUseCase   : EditRestaurantUseCase   = EditRestaurantUseCase()
    private  var addRestaurantUseCase    : CreateRestaurantUseCase = CreateRestaurantUseCase()
    private  var deleteRestaurantUseCase : DeleteRestaurantUseCase = DeleteRestaurantUseCase()

    init {
        initData()

    }
    fun initData() {
        listRestaurants = DaoRestaurant.myDao.listRestaurant().toMutableList() //llamamos al singleton.
    }

    fun iniciar(recyclerView: RecyclerView, requireActivity: FragmentActivity) {
        val context = requireActivity
        recyclerView.layoutManager = LinearLayoutManager(requireActivity)
        recyclerView.adapter = AdapterRestaurant(
            { pos -> delRestaurant(pos,recyclerView,context) }, { pos -> updateRestaurant(pos,recyclerView,context)})

    }

    fun delRestaurant(pos: Int,recyclerView : RecyclerView,context: Context){
        viewModelScope.launch {
            mostrarDialogoEliminarRestaurante(pos,recyclerView,context)
            progressBarLiveData.value = true //LiveData notifica del cambio.
            delay(2000)
            val data: List<Restaurant> =
                deleteRestaurantUseCase(1) //Invocamos a nuestro caso de uso (lógica de negocio).
            listRestaurants = data.toMutableList()
            data.let {
                restaurantListLiveData.value = it //LiveData notifica del cambio.
                progressBarLiveData.value = false //LiveData notifica del cambio.
            }
        }
    }

    fun updateRestaurant(pos:Int,recyclerView:RecyclerView,context:Context){
        viewModelScope.launch {
            progressBarLiveData.value = true //LiveData notifica del cambio.
            delay(2000)
            val data: List<Restaurant> =
                deleteRestaurantUseCase(1) //Invocamos a nuestro caso de uso (lógica de negocio).
            listRestaurants = data.toMutableList()
            data.let {
                restaurantListLiveData.value = it //LiveData notifica del cambio.
                progressBarLiveData.value = false //LiveData notifica del cambio.
            }
        }
    }


    fun listarRestarurants() {
        viewModelScope.launch {
            progressBarLiveData.value = true //LiveData notifica del cambio.
            delay(2000)
            val data: List<Restaurant> = listRestaurantUseCase() //Invocamos a nuestro caso de uso (lógica de negocio).
            listRestaurants = data.toMutableList()
            data.let {
                restaurantListLiveData.value = it //LiveData notifica del cambio.
                progressBarLiveData.value = false //LiveData notifica del cambio.
            }
        }
    }

    fun addRestaurant(recyclerView: RecyclerView, context: Context) {


    }



    // Nueva función para mostrar el diálogo de eliminación
    private fun mostrarDialogoEliminarRestaurante(pos: Int, recyclerView: RecyclerView,context: Context) {
        val dialog = DialogDeleteRestaurant(pos, listRestaurants[pos].name, this)
        dialog.setDeleteRestaurantDialogListener(object :
            DialogDeleteRestaurant.DeleteRestaurantDialogListener {
            override fun onDialogPositiveClick(pos: Int) {
                val adapter = recyclerView.adapter  as AdapterRestaurant
                Toast.makeText(context, "Borrado el Restaurante ${listRestaurants[pos].name}" +
                        " de la posición $pos", Toast.LENGTH_LONG).show()
                listRestaurants.removeAt(pos)
                // Notificar al adaptador sobre el cambio
                adapter.notifyItemRemoved(pos)
                adapter.notifyDataSetChanged()
            }

            override fun onDialogNegativeClick() {
                Toast.makeText(context, "Cancelado el borrado de  " +
                        "${listRestaurants[pos].name} de la posición $pos", Toast.LENGTH_LONG).show()
            }
        })
        dialog.show((context as AppCompatActivity).supportFragmentManager, "DialogDeleteRestaurant")
    }




    private fun mostrarDialogoNewRestaurant(recyclerView: RecyclerView,context: Context){
        val dialog = DialogNewRestaurant(object : DialogNewRestaurant.NewRestaurantDialogListener {
            override fun onDialogPositiveClick(
                newName: String,
                newCity: String,
                newProvince: String,
                newPhoneNumber: String,
                newImageUrl: String
            ) {
                // agregamos un nuevo restaurante
                val newRestaurant = Restaurant(newName, newCity, newProvince, newPhoneNumber,
                    newImageUrl)
                listRestaurants.add(newRestaurant)

                // Notificamos al adaptador sobre el cambio
                val adapter = recyclerView.adapter as AdapterRestaurant
                adapter.notifyItemInserted(listRestaurants.size - 1)


                Toast.makeText(context, "Nuevo restaurante agregado", Toast.LENGTH_LONG).show()
            }

            override fun onDialogNegativeClick() {
                // Acciones después de que el usuario hace clic en "Cancelar"
                Toast.makeText(context, "Cancelada la creacion de un nuevo Restaurante",
                    Toast.LENGTH_LONG).show()
            }
        })

        dialog.show((context as AppCompatActivity).supportFragmentManager, "DialogNewRestaurant")
    }

    override fun onDialogPositiveClick(
        pos: Int,
        newName: String,
        newCity: String,
        newProvince: String,
        newPhoneNumber: String,
        newImageUrl: String
    ) {
        TODO("Not yet implemented")
    }

    override fun onDialogNegativeClick() {
        TODO("Not yet implemented")
    }


}