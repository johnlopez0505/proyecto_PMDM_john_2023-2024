package com.john.proyecto_pmdm_john_2023_2024.data.dataSource

import com.john.proyecto_pmdm_john_2023_2024.data.models.user.User

object Users {
    val listUser : MutableList<User> = mutableListOf(
        User("john","lopezcon1@hotmail.com","1234"),
        User("juan","juan@hotmail.com","1234"),
        User("sonia","sonia@hotmail.com","1234"),
        User("dario","dario@hotmail.com","1234"),
        User("francisco","francisco@hotmail.com","1234")
    )

}

