package com.example.moviepicker.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepicker.MVP
import com.example.moviepicker.MainActivity
import com.example.moviepicker.R
import com.example.moviepicker.fragments.MainFragment
import com.example.moviepicker.model.Movie
import com.example.moviepicker.presenter.OnEndReachCallback
import com.example.moviepicker.presenter.OnItemClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.movie_item.view.*



class MovieAdapter(var items:ArrayList<Movie>,var presenter: MVP.Presenter) : RecyclerView.Adapter<MovieAdapter.MovieHolder>(){
    lateinit var onEndReachCallback:OnEndReachCallback
    lateinit var onItemClickListener:OnItemClickListener
    lateinit var recyclerView: RecyclerView
    lateinit var fragmentMn: FragmentManager
    private var mScrollListener: RecyclerView.OnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val mLayoutManager = recyclerView.layoutManager as GridLayoutManager
            val visibleItemCount =  mLayoutManager.getChildCount()
            val totalItemCount = mLayoutManager.getItemCount()
            val pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition()
            if (pastVisibleItems + visibleItemCount >= totalItemCount && visibleItemCount>=20) {
                Log.i("start",totalItemCount.toString() + " " + visibleItemCount )
                onEndReachCallback.onEndReachCallback()
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        initReach(view.context)
        onItemClickListener = presenter.getOnItemClick()
        return MovieHolder(view)
    }

    @Suppress("DEPRECATION")
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
        this.recyclerView.setOnScrollListener(mScrollListener)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(items.get(position),onItemClickListener)

        }


    override fun getItemCount(): Int {
        return items.size
    }

    fun initReach(context: Context) {
        onEndReachCallback = object : OnEndReachCallback {
            override fun onEndReachCallback() {
                Toast.makeText(context,"Reached end",Toast.LENGTH_LONG).show()
                Log.i("start","Reached end")
                presenter.loadData(items,context)
            }

        }
    }
    inner class MovieHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        fun bind(movie: Movie,onItemClickListener: OnItemClickListener){
            val picasso = Picasso.get()
            picasso.load("https://image.tmdb.org/t/p/w780" + movie.poster_path).into(itemView.card_image)
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(movie)
            }
        }
    }
}
