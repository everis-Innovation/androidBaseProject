package com.base.viper.network.callback

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class  BaseCallBack<T>: Callback<T> {

    var onResponse: ((Response<T>) -> Unit)? = null
    var onFailure: ((t: Throwable?) -> Unit)? = null

    override fun onFailure(call: Call<T>, t: Throwable) {
        onFailure?.invoke(t)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        onResponse?.invoke(response)
    }

    fun<T> Call<T>.enqueue(callback: BaseCallBack<T>.() -> Unit) {
        val baseCallBack = BaseCallBack<T>()
        callback.invoke(baseCallBack)
        this.enqueue(baseCallBack)
    }
}