package com.john.proyecto_pmdm_john_2023_2024.dao

import android.health.connect.datatypes.SexualActivityRecord
import android.service.autofill.UserData
import com.john.proyecto_pmdm_john_2023_2024.interfaceDao.DaoUserInterface
import com.john.proyecto_pmdm_john_2023_2024.models.User
import com.john.proyecto_pmdm_john_2023_2024.object_model.UserRepository


class DaoUser private constructor(): DaoUserInterface {
    companion object {
        val myDao: DaoUser by lazy{ //lazy delega a un primer acceso
            DaoUser() //Me creo sólo este objeto una vez.
        }
    }
    //Método que accede a la BBDD y devuelve todos los datos

    override fun getDataUser(): List<User> = UserRepository.listUser

    fun addUser(user: User) {
        UserRepository.listUser.add(user)
    }
}

