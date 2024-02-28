package com.john.proyecto_pmdm_john_2023_2024.data.network.service

import com.john.proyecto_pmdm_john_2023_2024.data.network.models.request.RequestLoginUser
import com.john.proyecto_pmdm_john_2023_2024.data.network.models.request.RequestRegisterUser
import com.john.proyecto_pmdm_john_2023_2024.data.network.models.responses.ResponseLogin
import com.john.proyecto_pmdm_john_2023_2024.data.network.models.responses.ResponseRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiServiceInterface {

    @POST("auth")
    suspend fun login(@Body loginUser : RequestLoginUser): Response<ResponseLogin>

    @POST("registro")
    suspend fun register(@Body registerUser: RequestRegisterUser) : Response<ResponseRegister>

}