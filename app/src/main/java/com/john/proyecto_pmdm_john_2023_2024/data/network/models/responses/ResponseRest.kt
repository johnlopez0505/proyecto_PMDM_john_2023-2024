package com.john.proyecto_pmdm_john_2023_2024.data.network.models.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseRest(

    @SerializedName("nombre")
    @Expose
    val nombre : String,

    @SerializedName("ciudad")
    @Expose
    val ciudad  : String,

    @SerializedName("provincia")
    @Expose
    val provincia: String,

    @SerializedName("telefono")
    @Expose
    val telefono : String,

    @SerializedName("imagen")
    @Expose
    val imagen : String?,

    @SerializedName("id")
    @Expose
    val id : Int,

    @SerializedName("id_usuario")
    @Expose
    val id_usuario : String,

    @SerializedName("file_img")
    @Expose
    val file_img : String,


    @SerializedName("insert_id")
    @Expose
    val insert_id : String,

    @SerializedName("details")
    @Expose
    val details : String


)
