package com.base.viper.network.repository

import com.base.viper.network.interfaces.GithubApiService


object SearchRepositoryProvider {

    fun provideSearchRepository(): SearchRepository {
        return SearchRepository(GithubApiService.Factory.create())
    }

}