package com.example.moviepicker.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepicker.MVP
import com.example.moviepicker.R
import com.example.moviepicker.adapter.MovieAdapter
import com.example.moviepicker.model.Movie
import com.example.moviepicker.model.MovieModel
import com.example.moviepicker.presenter.MoviePresenter
import com.example.moviepicker.repositories.LoadRepo
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment :Fragment(), MVP.View{
    lateinit var recycler_view:RecyclerView
    lateinit var adapter: MovieAdapter
    lateinit var list_of_movie:ArrayList<Movie>

    lateinit var repo: LoadRepo
    lateinit var model:MVP.Model
    lateinit var presenter:MVP.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View = inflater.inflate(R.layout.fragment_main,container,false)
        if (::adapter.isInitialized){
            recycler_view = view.recycler_main!!
            recycler_view.adapter = adapter
            recycler_view.layoutManager = GridLayoutManager(context,2)
        }
        else{
            init(view)
            loadData()
        }
        Log.i("onCreateView","onCreateView")
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("onStart","onStart")

    }


    override fun setvisible() {

    }

    override fun onReloadSucces() {
        Log.i("result1",list_of_movie.size.toString())
        adapter.notifyDataSetChanged()
    }

    override fun loadError(it: Throwable) {
        Log.i("result2",it.toString())
    }

    fun init(view:View){
        recycler_view = view.recycler_main!!
        list_of_movie = ArrayList()
        repo = LoadRepo()
        model = MovieModel(repo)
        presenter = MoviePresenter(this,model)
        adapter = MovieAdapter(list_of_movie,presenter)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = GridLayoutManager(context,2)
    }
    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
        Log.i("destroy","destroyed")
    }
    override fun fragmentManager():FragmentManager {
        return fragmentManager!!
    }
    fun loadData(){
        presenter.loadData(list_of_movie,context)
    }
}