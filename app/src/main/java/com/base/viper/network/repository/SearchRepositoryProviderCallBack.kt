package com.base.viper.network.repository

import com.base.viper.network.interfaces.ApiService


object SearchRepositoryProviderCallBack {

    fun provideSearchRepository(): SearchRepositoryCallBack {
        return SearchRepositoryCallBack(ApiService.Factory.create())
    }

}