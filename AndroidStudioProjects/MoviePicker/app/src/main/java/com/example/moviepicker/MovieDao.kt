package com.example.moviepicker

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviepicker.model.Movie
import io.reactivex.Observable
import java.util.*
import kotlin.collections.ArrayList

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getAll(): Observable<List<Movie>>

    @Query("DELETE FROM movies")
    fun deleteAll()

    @Query("SELECT * FROM movies WHERE id=:id")
    fun getElementById(id:Int): Movie

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieData: Movie)
}