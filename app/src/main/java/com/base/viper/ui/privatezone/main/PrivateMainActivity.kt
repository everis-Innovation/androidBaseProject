package com.base.viper.ui.privatezone.main

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.base.viper.R
import com.base.viper.data.app.model.MainViewModel
import com.base.viper.network.model.Result
import com.base.viper.network.repository.SearchRepositoryProvider
import com.base.viper.ui.privatezone.bindingdata.PostsDiffUtilCallback
import com.base.viper.ui.privatezone.bindingdata.SimpleRecyclerAdapter
import com.jakewharton.rxbinding2.widget.textChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_private_main.*
import java.util.concurrent.TimeUnit

import com.base.viper.network.model.User
import com.base.viper.network.repository.SearchRepositoryProviderCallBack
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


class PrivateMainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val disposable = CompositeDisposable()

    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    lateinit var call: Call<User>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_private_main)
        initList()
        getDataRxKotlin()
        getDataCallBack()
    }

    private fun getDataRxKotlin() {
        val repository = SearchRepositoryProvider.provideSearchRepository()

        compositeDisposable.add(
            repository.searchUsers("Lagos", "Java")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                        result ->
                    Log.d("Result", "There are ${result.items.size} Java developers in Lagos")
                }, { error ->
                    error.printStackTrace()
                })
        )
    }

    private fun getDataCallBack() {
        val repository = SearchRepositoryProviderCallBack.provideSearchRepository()

        repository.searchUsers("Lagos", "Java").enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>?, response: Response<Result>?) {

                var resutl: Result? = response?.body()
                Log.d("Result CALLBACK", "There are ${resutl?.items?.size} Java developers in Lagos")
            }

            override fun onFailure(call: Call<Result>?, t: Throwable?) {
            }
        })

    }

    fun initList() {
        var viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SimpleRecyclerAdapter(this, viewModel.oldFilteredPosts)

        searchInput
            .textChanges()
            .debounce(200, TimeUnit.MILLISECONDS)
            .subscribe {
                viewModel
                    .search(it.toString())
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        val diffResult = DiffUtil.calculateDiff(PostsDiffUtilCallback(viewModel.oldFilteredPosts, viewModel.filteredPosts))
                        viewModel.oldFilteredPosts.clear()
                        viewModel.oldFilteredPosts.addAll(viewModel.filteredPosts)
                        diffResult.dispatchUpdatesTo(recyclerView.adapter)
                    }.addTo(disposable)
            }.addTo(disposable)

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
        compositeDisposable.clear()
    }
}
