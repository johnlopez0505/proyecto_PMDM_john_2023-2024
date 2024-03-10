package com.john.proyecto_pmdm_john_2023_2024.data.network.service.restaurant


import com.john.proyecto_pmdm_john_2023_2024.data.network.models.request.RequestIdRestaurant
import com.john.proyecto_pmdm_john_2023_2024.data.network.models.request.RequestInsertRestaurant
import com.john.proyecto_pmdm_john_2023_2024.data.network.models.responses.ResponseCreate
import com.john.proyecto_pmdm_john_2023_2024.data.network.models.responses.ResponseRestaurant
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface RestaurantApiServiceInterface {

    @GET("restaurante")
    suspend fun listarRestaurant(@Header("api-key") token: String) : Response<ResponseRestaurant>

    @POST("restaurante")
    suspend fun createRestaurant(@Body restaurant : RequestInsertRestaurant,@Header("api-key") token: String): Response<ResponseCreate>

    @DELETE("restaurante")
    suspend fun deleteRestaurant(@Query("id") id : Int, @Header("api-key" ) token: String) :
            Response<ResponseRestaurant>

    @PUT("restaurante")
    suspend fun editRestaurant(@Query("id") id : Int,
                               @Body newRestaurant : RequestInsertRestaurant,
                               @Header("api-key" ) token : String) : Response<ResponseCreate>
}