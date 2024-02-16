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
import com.john.proyecto_pmdm_john_2023_2024.ui.adapter.ViewHRestaurant
import com.john.proyecto_pmdm_john_2023_2024.ui.view.DialogDeleteRestaurant
import com.john.proyecto_pmdm_john_2023_2024.ui.view.DialogEditRestaurant
import com.john.proyecto_pmdm_john_2023_2024.ui.view.DialogNewRestaurant
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class RestaurantViewModel : ViewModel(), DialogEditRestaurant.EditRestaurantDialogListener {
    var restaurantListLiveData = MutableLiveData<List<Restaurant>>() //repositorio observable.
    var listRestaurants: MutableList<Restaurant> = mutableListOf() // lista de objetos
    var progressBarLiveData = MutableLiveData<Boolean> () //progressbar observable
    var buttonAddRestauran = MutableLiveData<Restaurant>()
    private  var listRestaurantUseCase   : ListRestaurantUseCase   = ListRestaurantUseCase()
    private  var editRestaurantUseCase   : EditRestaurantUseCase   = EditRestaurantUseCase()
    private  var addRestaurantUseCase    : CreateRestaurantUseCase = CreateRestaurantUseCase()
    private  var deleteRestaurantUseCase : DeleteRestaurantUseCase = DeleteRestaurantUseCase()

    init {
        listarRestarurants()
    }
    //fun initData() {
        //listRestaurants = DaoRestaurant.myDao.listRestaurant().toMutableList() //llamamos al singleton.
      //  adapterRestaurant = AdapterRestaurant()
    //}


    //val v = ViewHRestaurant().setOnClickListener(pos)

    fun iniciar(recyclerView: RecyclerView, requireActivity: FragmentActivity) {
        val context = requireActivity
        recyclerView.layoutManager = LinearLayoutManager(requireActivity)


    }

    fun listarRestarurants() {
        viewModelScope.launch {
            progressBarLiveData.value = true //LiveData notifica del cambio.
            delay(2000)
            //listRestaurants = listRestaurantUseCase().toMutableList()
            restaurantListLiveData.value =
                listRestaurantUseCase().toMutableList()//Invocamos a nuestro caso de uso (lógica de negocio).
            progressBarLiveData.value = false //LiveData notifica del cambio.
        }
    }


    fun addRestaurant(recyclerView: RecyclerView, context: Context) {
        mostrarDialogoNewRestaurant(recyclerView,context)
        val restaurant = Restaurant("john","la dorada","Caldas","63232652","hola")
    }

    fun delRestaurant(pos: Int,recyclerView : RecyclerView,context: Context){
       mostrarDialogoEliminarRestaurante(pos,recyclerView,context)
    }

    fun updateRestaurant(pos:Int,recyclerView:RecyclerView,context:Context){
        mostrarDialogoEditarRestaurante(pos,recyclerView,context)
    }



    // Nueva función para mostrar el diálogo de eliminación
    private fun mostrarDialogoEliminarRestaurante(pos: Int, recyclerView: RecyclerView,context: Context) {
        val restaurantes = deleteRestaurantUseCase(pos)
        val dialog = DialogDeleteRestaurant(pos, restaurantes[pos].name, this)
        dialog.setDeleteRestaurantDialogListener(object :
            DialogDeleteRestaurant.DeleteRestaurantDialogListener {
            override fun onDialogPositiveClick(pos: Int) {
                val adapter = recyclerView.adapter  as AdapterRestaurant
                Toast.makeText(context, "Borrado el Restaurante ${restaurantes[pos].name}" +
                        " de la posición $pos", Toast.LENGTH_LONG).show()
                restaurantListLiveData.value = restaurantes

                adapter.notifyItemRemoved(pos)
                adapter.notifyDataSetChanged()
            }

            override fun onDialogNegativeClick() {
                Toast.makeText(context, "Cancelado el borrado de  " +
                        "${restaurantes[pos].name} de la posición $pos", Toast.LENGTH_LONG).show()
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
                restaurantListLiveData.value = addRestaurantUseCase(newRestaurant).toMutableList()
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

    private fun mostrarDialogoEditarRestaurante(pos: Int, recyclerView: RecyclerView,context: Context) {
        val listRestaurant = editRestaurantUseCase(pos)
        if (pos in 0 until listRestaurant.size) {
            val dialog = DialogEditRestaurant(pos, listRestaurant[pos], this)
            dialog.setEditRestaurantDialogListener(object :
                DialogEditRestaurant.EditRestaurantDialogListener{
                override fun onDialogPositiveClick(
                    pos: Int,
                    newName: String,
                    newCity: String,
                    newProvince: String,
                    newPhoneNumber: String,
                    newImageUrl: String
                ) {

                    // Lógica para guardar la edición del restaurante
                    val adapter = recyclerView.adapter as AdapterRestaurant
                    val editedRestaurant = Restaurant(newName, newCity, newProvince,
                        newPhoneNumber, newImageUrl)
                    restaurantListLiveData.value = listRestaurant
                    //listRestaurants[pos] = editedRestaurant
                    //adapter.notifyItemChanged(pos)
                    Toast.makeText(context, "Restaurante editado correctamente",
                        Toast.LENGTH_LONG).show()
                }

                override fun onDialogNegativeClick() {
                    // Acciones después de que el usuario hace clic en "Cancelar"
                    Toast.makeText(context, "Edición del restaurante cancelada",
                        Toast.LENGTH_LONG).show()
                }

            })

            dialog.show((context as AppCompatActivity).supportFragmentManager,
                "DialogEditRestaurant")
        } else {
            // Manejar el caso en el que pos no es válido
            Toast.makeText(context, "Posición no válida", Toast.LENGTH_SHORT).show()
        }
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