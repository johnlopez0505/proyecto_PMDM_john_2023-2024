package com.john.proyecto_pmdm_john_2023_2024.domain.model.user

data class User(var id: Int,
                var token: String?,
                var name: String?,
                var email:String?,
                var password:String?,
                val disponible: String?,
                var imagen: String?){


    constructor(email: String, password: String):
            this(0, "","", email, password, "", "")

    constructor(token: String?):
            this(0, token,"", "", "", "", "")

}
