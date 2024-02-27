package com.john.proyecto_pmdm_john_2023_2024.data.dataSource

import com.john.proyecto_pmdm_john_2023_2024.data.models.user.Usuarios

object Users {
    val listUser : MutableList<Usuarios> = mutableListOf(
        Usuarios("john","lopezcon1@hotmail.com","1234"),
        Usuarios("juan","juan@hotmail.com","1234"),
        Usuarios("sonia","sonia@hotmail.com","1234"),
        Usuarios("dario","dario@hotmail.com","1234"),
        Usuarios("francisco","francisco@hotmail.com","1234")
    )

}

