package com.base.viper.ui.publiczone.login

import android.os.Bundle
import android.view.View
import com.base.viper.R
import com.base.viper.common.base.BaseActivity
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class LoginActivity : BaseActivity(),
    LoginContracts.View {

    private val SECS_MULTICLICKS = 3L
    private val SECS_WAIT_NEXT_SCREEN = 2L

    var presenter: LoginContracts.Presenter? = LoginPresenter(this)
    var showLoginButton: Boolean = false

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //TODO create the layout and add it here
        presenter?.onCreate(intent.extras)
        setValidateEditTexts()
        setActionButtom()
    }

    override fun onResume() {
        super.onResume()
        presenter?.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter?.onPause()
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

    //endregion

    //region View
    fun setActionButtom() {
        RxView.clicks(buttonLogin)
            .throttleFirst(SECS_MULTICLICKS, TimeUnit.SECONDS)
            .doOnNext { showLoadingDialog() }
            .debounce(SECS_WAIT_NEXT_SCREEN, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread())
            .map {
                presenter?.continueAction()
            }
            .doAfterNext { closeLoadingDialog() }
            .subscribe()
    }

    fun setValidateEditTexts() {
        RxTextView.afterTextChangeEvents(editTextEmail)
            .skipInitialValue()
            .map {
                emailWrapper.error = null
                buttonEnable(View.VISIBLE)
                it.view().text.toString()
            }
            .debounce(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread())
            .compose(presenter?.evaluateLenght())
            .compose(presenter?.evaluatePattern())
            .compose(presenter?.retryAction {
                emailWrapper.error = it.message
                buttonEnable(View.GONE)
            })
            .subscribe()

        RxTextView.afterTextChangeEvents(editTextPassword)
            .skipInitialValue()
            .map {
                passwordWrapper.error = null
                buttonEnable(View.VISIBLE)
                it.view().text.toString()
            }
            .debounce(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread())
            .compose(presenter?.evaluateLenght())
            .compose(presenter?.retryAction {
                passwordWrapper.error = it.message
                buttonEnable(View.GONE)
            })
            .subscribe()
    }

    fun buttonEnable(state: Int) {
        buttonLogin.visibility = state
    }

    //TODO: Implement your View methods here

    //endregion
}
