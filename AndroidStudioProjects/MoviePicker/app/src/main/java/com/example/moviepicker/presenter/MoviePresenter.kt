package com.example.moviepicker.presenter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.moviepicker.MVP
import com.example.moviepicker.R
import com.example.moviepicker.fragments.DetailFragment
import com.example.moviepicker.model.Movie
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MoviePresenter(var view:MVP.View?,var model:MVP.Model): MVP.Presenter {
    lateinit var onItemClickListener:OnItemClickListener
    lateinit var disposable:Disposable
    override fun getOnItemClick(): OnItemClickListener {
        return onItemClickListener
    }

    init {
        onItemClickListener = object : OnItemClickListener{
            override fun onItemClick(movie: Movie) {
                val fragment = DetailFragment()
                val bundle = Bundle()
                bundle.putParcelable("movie",movie)
                fragment.setArguments(bundle)
                val transaction = view!!.fragmentManager().beginTransaction()
                transaction.replace(R.id.frame_layout,fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
    }
    override fun onDestroy() {
        view = null
        disposable.dispose()
    }

    @SuppressLint("CheckResult")
    override fun loadData(list:ArrayList<Movie>, context: Context?) {
        disposable = model.getPostsByPop()
            .subscribeOn(Schedulers.single())
            /*.doOnNext{
                model.deleteAll(context)
                model.saveAllToDb(context,it.list!! as ArrayList<Movie>)
            }
            .doOnError{
                model.getFromDb(context)
                    .subscribe({
                        list.addAll(it)
                        view?.onReloadSucces()
                    },{

                    })
            }*/
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                list.addAll(it.list!!)
                view?.onReloadSucces()
            },{
                view?.loadError(it)
                view?.onReloadSucces()
            })

    }

}
interface OnEndReachCallback{
    fun onEndReachCallback()
}
interface OnItemClickListener{
    fun onItemClick(movie: Movie)
}