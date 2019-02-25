package com.base.viper.common.base

class BaseInteractor(var output: BaseContracts.InteractorOutput?) :
    BaseContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}
