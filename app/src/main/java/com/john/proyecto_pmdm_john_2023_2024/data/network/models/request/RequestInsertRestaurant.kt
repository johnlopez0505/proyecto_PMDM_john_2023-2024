package com.john.proyecto_pmdm_john_2023_2024.data.network.models.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RequestInsertRestaurant(

    @SerializedName("id_usuario")
    @Expose
    val id_usuario: String?,

    @SerializedName("nombre")
    @Expose
    val nombre: String,

    @SerializedName("ciudad")
    @Expose
    val ciudad: String,

    @SerializedName("provincia")
    @Expose
    val provincia: String,

    @SerializedName("telefono")
    @Expose
    val telefono: String,

    @SerializedName("imagen")
    @Expose
    val imagen: String?,

    )
