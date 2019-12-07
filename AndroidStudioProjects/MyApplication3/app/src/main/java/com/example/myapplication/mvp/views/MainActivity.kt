package com.example.myapplication.mvp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class MainActivity : AppCompatActivity() {
    val newsFragment: Fragment = NewsListFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragments()

    }
    fun loadFragments(){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.container, newsFragment)/*.hide(newsFragment)*/
            .commit()
    }

}
