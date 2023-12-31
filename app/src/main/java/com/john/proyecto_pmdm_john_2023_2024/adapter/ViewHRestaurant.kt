package com.john.proyecto_pmdm_john_2023_2024.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.john.proyecto_pmdm_john_2023_2024.databinding.ItemRestaurantBinding
import com.john.proyecto_pmdm_john_2023_2024.models.Restaurant

class ViewHRestaurant (view: View, var deleteOnClick:
    (Int) -> Unit, var updateOnClick: (Int) -> Unit):
    RecyclerView.ViewHolder (view){

    private lateinit var binding: ItemRestaurantBinding
    init {
        binding = ItemRestaurantBinding.bind(view)
    }
    //método que se encarga de mapear los item por propiedad del modelo.
    fun renderize(hotel : Restaurant){
        binding.txtviewName.setText(hotel. name)
        binding.txtviewCity.setText(hotel. city)
        binding.txtviewProvince.setText(hotel. province)
        binding.txtviewPhone.setText(hotel. phone)
        Glide
            .with( itemView.context)
            .load(hotel. image)
            .centerCrop()
            .into( binding.ivRestaurant)

        setOnClickListener(adapterPosition)
    }

    fun setOnClickListener(position : Int) {
        binding.btnEdit.setOnClickListener {
            updateOnClick(position)
        }
        binding.btnDelete.setOnClickListener {
            deleteOnClick(position)
        }
    }

}