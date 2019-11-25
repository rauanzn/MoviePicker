package com.example.moviepicker

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.example.moviepicker.model.Movie
import com.example.moviepicker.model.Result
import com.example.moviepicker.presenter.OnItemClickListener
import io.reactivex.Observable

interface MVP {
    interface View{
        fun fragmentManager():FragmentManager
        fun setvisible()
        fun onReloadSucces()
        fun loadError(it:Throwable)
    }
    interface Presenter{
        fun loadData(list: ArrayList<Movie>,context: Context?)
        fun onDestroy()
        fun getOnItemClick(): OnItemClickListener
    }
    interface Model{
        fun getPostsByPop(): Observable<Result>
        fun getPostsByRat():Observable<Result>
        fun saveAllToDb(context:Context?,list_of_movies:ArrayList<Movie>)
        fun deleteAll(context: Context?)
        fun getFromDb(context: Context?) :Observable<List<Movie>>
    }
}