package com.john.proyecto_pmdm_john_2023_2024.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.john.proyecto_pmdm_john_2023_2024.R
import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.RepositoryRestaurant
import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.Restaurant

class AdapterRestaurant(private var deleteOnClick: (Int) -> Unit,
                        private var updateOnClick: (Int) -> Unit,
                        private var sendInfo:      (Int) -> Unit
) : RecyclerView.Adapter<ViewHRestaurant>(){
    var restaurantRepository: List<Restaurant> = RepositoryRestaurant.restaurants//cargo del repsitorio dememoria.


    /*
    Método que crea la view del ViewHolderHotel
    */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHRestaurant {
        val layoutInflater = LayoutInflater.from(parent. context)//objeto para crear la vista.
        val layoutItemRestaurant = R.layout.item_restaurant //accedo al xml del item a crear.
        return ViewHRestaurant(
            layoutInflater.inflate(layoutItemRestaurant, parent, false),
            deleteOnClick,
            updateOnClick,
            sendInfo

        )

    }

    /*
    Este método, debe renderizar todos los datos o propiedades de cada hotel con la view.
    Accedemos al objeto por medio de position
    */
    override fun onBindViewHolder(holder: ViewHRestaurant, position: Int) {
        holder.renderize(restaurantRepository[position]) //renderizamos la view.



    }
    /*
    Este método, devuelve el número de objetos a representar en el recyclerView.
    */
    override fun getItemCount(): Int = restaurantRepository.size
}