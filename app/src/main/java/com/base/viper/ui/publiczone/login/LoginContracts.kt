package com.base.viper.ui.publiczone.login

import com.base.viper.common.base.BaseContracts
import io.reactivex.ObservableTransformer

object LoginContracts {

    interface View : BaseContracts.View {
        //TODO
    }

    interface Presenter : BaseContracts.Presenter {
        fun continueAction()
        fun evaluateLenght(): ObservableTransformer<String, String>
        fun evaluatePattern(): ObservableTransformer<String, String>
        fun retryAction(onError: (ex: Throwable) -> Unit): ObservableTransformer<String, String>
    }

    interface Interactor : BaseContracts.Interactor {
        //TODO
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : BaseContracts.Router {
        fun continueAction()
    }

}
