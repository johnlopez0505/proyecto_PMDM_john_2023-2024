package com.john.proyecto_pmdm_john_2023_2024.data.network.models.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.Restaurant

data class ResponseRestaurant(
    @SerializedName("result")
    @Expose
    val result: String,

    @SerializedName("restaurantes")
    @Expose
    val restaurantes : MutableList<Restaurant>
)
