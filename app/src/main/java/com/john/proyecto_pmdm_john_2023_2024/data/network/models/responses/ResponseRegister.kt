package com.john.proyecto_pmdm_john_2023_2024.data.network.models.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseRegister(
    @SerializedName("result")
    @Expose
    val result: String,

    @SerializedName("insert_id")
    @Expose
    val id : String,

    @SerializedName("details")
    @Expose
    val details : String,




) {
}
