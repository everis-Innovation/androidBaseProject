package com.base.viper.ui.publiczone.login

class LoginInteractor(var output: LoginContracts.InteractorOutput?) :
    LoginContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}
