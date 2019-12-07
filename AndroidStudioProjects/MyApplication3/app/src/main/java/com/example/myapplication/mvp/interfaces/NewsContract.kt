package com.example.myapplication.mvp.interfaces

import android.content.Context
import io.reactivex.Observable

interface NewsContract{
    interface BaseModel{
        fun getFromApi():Observable<Any>
        fun getFromLocalStorage():Observable<Any>

    }
    interface BasePresenter{
        fun loadData(list: ArrayList<Any>,context: Context?):Observable<Any>
        fun onDestroy()
    }
    interface BaseView{
        fun noInternetConnection()
        fun onReloadSuccess()
        fun onReloadError()
    }
}