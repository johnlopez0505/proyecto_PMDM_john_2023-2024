package com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant

class DaoUsers {
    companion object {
        val myUser: DaoUsers by lazy{ //lazy delega a un primer acceso
            DaoUsers() //Me creo sólo este objeto una vez.
        }
    }
}