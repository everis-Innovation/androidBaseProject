package com.base.viper.network.interfaces

import com.base.viper.network.model.Result
import com.base.viper.network.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {

    @GET("search/users")
    fun search(@Query("q") query: String,
               @Query("page") page: Int = 1,
               @Query("per_page") perPage: Int = 20): Call<Result>

}