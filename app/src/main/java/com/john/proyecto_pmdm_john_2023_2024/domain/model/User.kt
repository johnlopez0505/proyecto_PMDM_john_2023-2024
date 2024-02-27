package com.john.proyecto_pmdm_john_2023_2024.domain.model

data class User(var id: Int,
                var token: String,
                var email:String,
                var password:String?,
                val disponible: Int,
                var imagen: String){


    constructor(email: String, password: String):
            this(0, "", email, password, 0, "")

}
