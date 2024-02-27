package com.john.proyecto_pmdm_john_2023_2024.data.network.models.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ResponseLogin (

    @SerializedName("result")
    @Expose
    val result: String,

    @SerializedName("token")
    @Expose
    val token : String,

    @SerializedName("id")
    @Expose
    val id : Int,

    @SerializedName("nombre")
    @Expose
    val name : String,

    @SerializedName("email")
    @Expose
    val email : String,

    @SerializedName("password")
    @Expose
    val password : String,

    @SerializedName("disponible")
    @Expose
    val disponible : Int,

    @SerializedName("imagen")
    @Expose
    val image : String
    ){
}