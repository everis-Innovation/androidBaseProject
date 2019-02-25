package com.base.viper.ui.publiczone.login

import android.app.Activity
import android.os.Bundle
import com.base.viper.utils.validators.PatternsLoginValidates
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

class LoginPresenter(var view: LoginContracts.View?) : LoginContracts.Presenter,
    LoginContracts.InteractorOutput {

    var interactor: LoginContracts.Interactor? = LoginInteractor(this)
    var router: LoginContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = LoginRouter(activity)

        bundle?.let {
            //you can delete this if there's no need to get extras from the intent
            //TODO: Do something
        }
    }

    override fun onDestroy() {
        view = null
        interactor?.unregister()
        interactor = null
        router?.unregister()
        router = null
    }

    override fun continueAction() {
       router?.continueAction()
    }

    override fun evaluateLenght(): ObservableTransformer<String, String> {
        return PatternsLoginValidates.create().lengthGreaterThanSix
    }

    override fun evaluatePattern(): ObservableTransformer<String, String> {
        return PatternsLoginValidates.create().verifyEmailPattern
    }

    override fun retryAction(onError: (ex: Throwable) -> Unit): ObservableTransformer<String, String> =
        ObservableTransformer { observable ->
            observable.retryWhen { errors ->
                errors.flatMap {
                    onError(it)
                    Observable.just("")
                }
            }
        }

    //endregion

    //region Presenter

    //TODO: Implement your Presenter methods here

    //endregion

    //region InteractorOutput

    //TODO: Implement your InteractorOutput methods here

    //endregion
}
