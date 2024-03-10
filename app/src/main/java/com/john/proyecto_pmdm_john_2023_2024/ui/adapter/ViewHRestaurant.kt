package com.john.proyecto_pmdm_john_2023_2024.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.john.proyecto_pmdm_john_2023_2024.databinding.ItemRestaurantBinding
import com.john.proyecto_pmdm_john_2023_2024.domain.model.restaurant.Restaurant

class ViewHRestaurant (view: View,  var deleteOnClick:
    (Int) -> Unit, var updateOnClick: (Int) -> Unit, var sendInfo: (Int) -> Unit
):RecyclerView.ViewHolder (view){

    private lateinit var binding: ItemRestaurantBinding
    init {
        binding = ItemRestaurantBinding.bind(view)
    }
    //método que se encarga de mapear los item por propiedad del modelo.
    fun renderize(restaurant : Restaurant){
        binding.txtviewName.text = restaurant. nombre
        binding.txtviewCity.text = restaurant. ciudad
        binding.txtviewProvince.text = restaurant. provincia
        binding.txtviewPhone.text = restaurant. telefono
        Glide
            .with( itemView.context)
            .load(restaurant. imagen)
            .centerCrop()
            .into( binding.ivRestaurant)

        setOnClickListener(adapterPosition)
    }
    private fun setOnClickListener(position : Int) {
        binding.btnDelete.setOnClickListener{
            deleteOnClick(position)
        }

        binding.btnEdit.setOnClickListener{
            updateOnClick(position)
        }
        binding.btnDescripcion.setOnClickListener{
            sendInfo(position)
        }
    }
}