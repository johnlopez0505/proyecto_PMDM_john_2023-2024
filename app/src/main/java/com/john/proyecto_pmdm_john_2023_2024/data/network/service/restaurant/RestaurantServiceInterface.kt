package com.john.proyecto_pmdm_john_2023_2024.data.network.service.restaurant


import com.john.proyecto_pmdm_john_2023_2024.data.network.models.responses.ResponseRestaurant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface RestaurantServiceInterface {

    @GET("restaurnate")
    suspend fun listarRestaurant(@Header("api-key") token: String) : Response<ResponseRestaurant>
}