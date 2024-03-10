package com.john.proyecto_pmdm_john_2023_2024.data.network.models.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseCreate(
    @SerializedName("result")
    @Expose
    val result : String,

    @SerializedName("insert_id")
    @Expose
    val insert_id : String,

    @SerializedName("file_img")
    @Expose
    val file_img : String



)
