package com.john.proyecto_pmdm_john_2023_2024.data.models.user

class Usuarios (
    var name: String,
    var email: String,
    var password: String
)
{
    override fun toString(): String {
        return "Usuario(name='$name', email='$email', password='$password')"
    }
}