package com.john.proyecto_pmdm_john_2023_2024.interfaceDao

import com.john.proyecto_pmdm_john_2023_2024.models.User

interface DaoUserInterface {
    fun getDataUser(): List<User>
}