package com.base.viper.network.repository

import com.base.viper.network.interfaces.GithubApiService

import com.base.viper.network.model.Result
/**
 * Repository method to access search functionality of the api service
 */
class SearchRepository(val apiService: GithubApiService) {

    fun searchUsers(location: String, language: String): io.reactivex.Observable<Result> {
        return apiService.search(query = "location:$location language:$language")
    }

}