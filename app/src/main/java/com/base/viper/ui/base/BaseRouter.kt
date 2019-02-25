package com.base.viper.common.base

import android.app.Activity

class BaseRouter(var activity: Activity?) : BaseContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}
