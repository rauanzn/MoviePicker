package com.example.myapplication.mvp.presenters

import android.content.Context
import com.example.myapplication.mvp.interfaces.NewsContract
import io.reactivex.Observable
import retrofit2.Retrofit

class NewsListFragmentPresenter(var view: NewsContract.BaseView?) :
    NewsContract.BasePresenter{

    override fun loadData(list: ArrayList<Any>, context: Context?): Observable<Any> {

    }

    override fun onDestroy() {
        view = null
    }
    fun getRetrofit():Retrofit{
        Retrofit
    }

}