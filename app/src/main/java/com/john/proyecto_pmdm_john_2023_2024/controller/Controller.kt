package com.john.proyecto_pmdm_john_2023_2024.controller

import android.content.Context
import com.john.proyecto_pmdm_john_2023_2024.adapter.AdapterRestaurant
import android.widget.Toast
import com.john.proyecto_pmdm_john_2023_2024.MainActivity
import com.john.proyecto_pmdm_john_2023_2024.dao.DaoRestaurant
import com.john.proyecto_pmdm_john_2023_2024.models.Restaurant

class Controller(private val context: Context){
    lateinit var listRestaurants : MutableList<Restaurant> //lista de objetos
    init {
        initData()
    }
    fun initData(){
        // listHotels = DaoHotels2.myDao.toMutableList()
        listRestaurants = DaoRestaurant. myDao.getDataRestaurant(). toMutableList() //llamamos al singleton.

    }
    fun loggOut() {
        Toast.makeText( context, "He mostrado los datos en pantalla", Toast. LENGTH_LONG).show()
        listRestaurants.forEach{
            println (it)
        }
    }

    // Cargamos nuestro AdapterHotgel al adapter del RecyclerView
    fun setAdapter() {
        val myActivity = context as MainActivity
        myActivity.binding.myRecyclerView.adapter = AdapterRestaurant(listRestaurants,
            { pos -> delRestaurant(pos) }, { pos -> updateRestaurant(pos) })


    }

    fun delRestaurant(pos: Int) {
        val myActivity = context as MainActivity
        val adapter = myActivity.binding.myRecyclerView.adapter as AdapterRestaurant

        Toast.makeText(context, "Borrado el Restaurante de la posición $pos", Toast.LENGTH_LONG).show()

        listRestaurants.removeAt(pos)
        adapter.notifyItemRemoved(pos)
        adapter.notifyDataSetChanged()
    }


    fun updateRestaurant(pos: Int) {
        val myActivity = context as MainActivity
        val adapter = myActivity.binding.myRecyclerView.adapter as AdapterRestaurant

        val RestaurantToUpdate = listRestaurants[pos]

        // Implementa la lógica para la actualización aquí
        val restaurant = Restaurant(
            "Panaceite",  // Puedes solicitar al usuario que ingrese un nuevo nombre
            RestaurantToUpdate.city,
            RestaurantToUpdate.province,
            "953 24 06 30",
            "https://media-cdn.tripadvisor.com/media/photo-s/0d/9b/47/ac/" +
                    "el-bar-y-su-terraza.jpg"
        )
        Toast.makeText(context, "Actualizado el Restaurante de la posición $pos", Toast.LENGTH_LONG).show()

        // Actualiza el hotel en la lista
        listRestaurants[pos] = restaurant

        // Notifica al adaptador sobre el cambio
        adapter.notifyItemChanged(pos)
    }

    fun addRestaurant() {
        val myActivity = context as MainActivity
        //myActivity.binding.myRecyclerView.adapter = AdapterRestaurant(listRestaurants,
           // { pos -> delRestaurant(pos) }, { pos -> updateRestaurant(pos) })

            Toast.makeText(context, "Creado un nuevo Restaurante ", Toast.LENGTH_LONG).show()
            listRestaurants.add(
                Restaurant("La tabernilla de Jose",
                    "Jaén",
                    "Jaén",
                    " 678 67 51 35",
                    "https://lh5.googleusercontent.com/p/AF1QipNUpR_WozbYMw4JO6loQlvAwr9Xdybxa7Uq6qln"))
            myActivity.binding.myRecyclerView.adapter?.notifyItemInserted(listRestaurants.size)
        setAdapter()

    }

    fun back() {
        val myActivity = context as MainActivity
        myActivity.finish()
    }


}