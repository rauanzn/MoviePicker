package com.example.moviepicker.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.moviepicker.MVP
import com.example.moviepicker.MovieDatabase
import com.example.moviepicker.R
import com.example.moviepicker.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.view.*

class DetailFragment : Fragment(), MVP.View {
    override fun fragmentManager():FragmentManager {
        return fragmentManager!!
    }

    override fun setvisible() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onReloadSucces() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadError(it: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View = inflater.inflate(R.layout.fragment_detail,container,false)
        if (arguments != null) {
            val movie = arguments!!.getParcelable<Movie>("movie")
            val picasso = Picasso.get()
            val back_path = "https://image.tmdb.org/t/p/original"+movie?.backdrop_path
            Log.i("back_path",movie!!.title)
            picasso.load(back_path).into(view.imageviewBig)
            view.textviewTitle.setText(movie?.title)
            view.originalTitleValue.setText(movie?.original_title)
            view.textviewDateValue.setText(movie?.release_date)
            view.textviewDescriptionValue.setText(movie?.overview)
            view.textviewRatingValue.setText(movie?.popularity.toString())
        }
        return view
    }
}