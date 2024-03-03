package com.john.proyecto_pmdm_john_2023_2024.data.network.models.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RequestRegisterUser(

    @SerializedName("email")
    @Expose
    val email : String,

    @SerializedName("password")
    @Expose
    val password : String,

    @SerializedName("nombre")
    @Expose
    val nombre  : String,

    @SerializedName("disponible")
    @Expose
    val disponible : String,

    @SerializedName("imagen")
    @Expose
    val image : String?

)
