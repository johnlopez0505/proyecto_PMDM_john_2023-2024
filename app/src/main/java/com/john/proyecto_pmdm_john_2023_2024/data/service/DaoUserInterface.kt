package com.john.proyecto_pmdm_john_2023_2024.data.service

import com.john.proyecto_pmdm_john_2023_2024.data.models.user.Usuarios

interface DaoUserInterface {
    fun getDataUser(): List<Usuarios>
}