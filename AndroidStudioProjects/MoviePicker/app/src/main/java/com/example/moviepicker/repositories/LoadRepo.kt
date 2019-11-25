package com.example.moviepicker.repositories

import android.content.Context
import com.example.moviepicker.GSON
import com.example.moviepicker.MovieDatabase
import com.example.moviepicker.model.Movie
import com.example.moviepicker.model.Result
import com.example.moviepicker.utils.UrlUtils
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

//We can do it with LoadRepo interface which can be implemented by this class
class LoadRepo{
    var page:Int = 0
    fun getPostsByPop(): Observable<Result> {
        val retrofit = getRetrofit(UrlUtils.BASE_URL_BY_POP_DESC)
        val observableByPop = retrofit.create(GSON::class.java).getAll(page)
        return observableByPop
    }

    fun getPostsByRat(): Observable<Result> {
        val retrofit = getRetrofit(UrlUtils.BASE_URL_BY_VOTE_AVERAGE_DESC)
        val observableByPop = retrofit.create(GSON::class.java).getAll(page)
        return observableByPop
    }

    fun getFromDb(context: Context?) :Observable<List<Movie>>{
        val db = MovieDatabase.getInstance(context)
        return db?.MovieDataDao()?.getAll()!!
    }
    fun saveAllToDb(context: Context?,list_of_movies :ArrayList<Movie>){
        var db:MovieDatabase? = MovieDatabase.getInstance(context)
        for (i in list_of_movies){
            db?.MovieDataDao()?.insert(i)
        }
    }
    fun deleteAll(context: Context?){
        var db:MovieDatabase? = MovieDatabase.getInstance(context)
        db?.MovieDataDao()?.deleteAll()
    }
    fun getRetrofit(base_url:String):Retrofit{
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .baseUrl(base_url)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}