package com.base.viper.ui.publiczone.login

import android.app.Activity
import android.content.Intent
import com.base.viper.ui.privatezone.main.PrivateMainActivity

class LoginRouter(var activity: Activity?) : LoginContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router
    override fun continueAction() {
        val intent = Intent(activity, PrivateMainActivity::class.java)
               activity?.startActivity(intent)
    }

    //TODO: Implement your Router methods here

    //endregion
}
