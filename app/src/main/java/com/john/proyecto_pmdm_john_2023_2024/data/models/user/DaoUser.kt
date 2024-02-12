package com.john.proyecto_pmdm_john_2023_2024.data.models.user

import com.john.proyecto_pmdm_john_2023_2024.data.service.DaoUserInterface
import com.john.proyecto_pmdm_john_2023_2024.data.dataSource.Users


class DaoUser private constructor(): DaoUserInterface {
    companion object {
        val myDao: DaoUser by lazy{ //lazy delega a un primer acceso
            DaoUser() //Me creo sólo este objeto una vez.
        }
    }
    //Método que accede a la BBDD y devuelve todos los datos

    override fun getDataUser(): List<User> = Users.listUser

    fun addUser(user: User) {
        Users.listUser.add(user)
    }
}

