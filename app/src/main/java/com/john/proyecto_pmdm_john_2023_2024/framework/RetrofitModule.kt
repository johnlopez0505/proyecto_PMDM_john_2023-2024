package com.john.proyecto_pmdm_john_2023_2024.framework


import com.john.proyecto_pmdm_john_2023_2024.data.network.service.UserApiServiceInterface
import com.john.proyecto_pmdm_john_2023_2024.data.network.service.restaurant.RestaurantApiServiceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private const val URL_BASE_RETROFIT = "http://10.0.2.2/api-restaurantes/endp/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit
        .Builder()
        .baseUrl(URL_BASE_RETROFIT)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideServiceApi(retrofit: Retrofit): UserApiServiceInterface =
        retrofit
            .create(UserApiServiceInterface::class.java)

    @Singleton
    @Provides
    fun provideServiceApiRestaurant(retrofit: Retrofit): RestaurantApiServiceInterface =
        retrofit
            .create(RestaurantApiServiceInterface::class.java)

}
