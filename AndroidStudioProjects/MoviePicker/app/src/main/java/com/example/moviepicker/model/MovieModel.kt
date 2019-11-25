package com.example.moviepicker.model

import android.content.Context
import com.example.moviepicker.MVP
import com.example.moviepicker.repositories.LoadRepo
import io.reactivex.Observable

class MovieModel(var repo: LoadRepo) : MVP.Model{
    lateinit var repository: LoadRepo
    init {
        repository = repo
    }
    override fun getPostsByPop(): Observable<Result> {
        repo.page++
        return repo.getPostsByPop()
    }

    override fun getPostsByRat(): Observable<Result> {
        repo.page++
        return repo.getPostsByRat()
    }
    override fun saveAllToDb(context:Context?,list_of_movies:ArrayList<Movie>){
        repo.saveAllToDb(context,list_of_movies)
    }
    override fun deleteAll(context: Context?){
        repo.deleteAll(context)
    }

    override fun getFromDb(context: Context?): Observable<List<Movie>> {
        return repo.getFromDb(context)
    }
}