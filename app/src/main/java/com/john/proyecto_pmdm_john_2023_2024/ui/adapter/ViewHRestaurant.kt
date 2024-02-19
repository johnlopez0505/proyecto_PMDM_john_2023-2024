package com.john.proyecto_pmdm_john_2023_2024.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.john.proyecto_pmdm_john_2023_2024.databinding.ItemRestaurantBinding
import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.Restaurant

class ViewHRestaurant (view: View,  var deleteOnClick:
    (Int) -> Unit, var updateOnClick: (Int) -> Unit, var sendInfo: (Int) -> Unit
):RecyclerView.ViewHolder (view){

    private lateinit var binding: ItemRestaurantBinding
    init {
        binding = ItemRestaurantBinding.bind(view)
    }
    //método que se encarga de mapear los item por propiedad del modelo.
    fun renderize(hotel : Restaurant){
        binding.txtviewName.text = hotel. name
        binding.txtviewCity.text = hotel. city
        binding.txtviewProvince.text = hotel. province
        binding.txtviewPhone.text = hotel. phone
        Glide
            .with( itemView.context)
            .load(hotel. image)
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