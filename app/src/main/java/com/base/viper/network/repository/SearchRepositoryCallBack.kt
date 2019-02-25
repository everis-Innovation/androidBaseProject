package com.base.viper.network.repository

import com.base.viper.network.interfaces.ApiInterface
import com.base.viper.network.model.Result
import com.base.viper.network.model.User
import retrofit2.Call

/**
 * Repository method to access search functionality of the api service
 */
class SearchRepositoryCallBack(val apiService: ApiInterface) {

    fun searchUsers(location: String, language: String): Call<Result> {
        return apiService.search(query = "location:$location language:$language")
    }

}