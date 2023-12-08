package com.john.proyecto_pmdm_john_2023_2024.object_model

import com.john.proyecto_pmdm_john_2023_2024.models.User

object UserRepository {
    val listUser : MutableList<User> = mutableListOf(
        User("john","lopezcon1@hotmail.com","1234"),
        User("juan perez","juan@hotmail.com","1234"),
        User("sonia rodriguez","sonia@hotmail.com","1234"),
        User("dario romero","dario@hotmail.com","1234"),
        User("francisco romero","francisco@hotmail.com","1234")
    )
}