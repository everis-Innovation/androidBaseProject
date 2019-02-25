package com.base.viper.ui.base

import android.app.Fragment
import android.content.Context
import android.content.ContextWrapper
import com.base.viper.common.base.BaseActivity
import com.base.viper.common.base.BaseContracts

abstract class BaseFragment: Fragment(), BaseContracts.View {

    fun getBaseActivity(): BaseActivity? {
        var context = context
        while (context is ContextWrapper) {
            if (context is BaseActivity) {
                return context
            }
            context = context.baseContext
        }
        return null
    }

    override fun getActivityContext(): Context? {
        return getBaseActivity()
    }

    override fun showLoadingDialog() {
        getBaseActivity()?.showLoadingDialog()
    }
}