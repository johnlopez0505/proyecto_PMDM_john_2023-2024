package com.john.proyecto_pmdm_john_2023_2024.domain.model.user

data class RegisterUser(
    val email: String,
    val password: String,
    val nombre: String,
    val disponible: String,
    val imagen: String?
)
