package com.example.moviepicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.moviepicker.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainFragment = MainFragment()
        val fmanager = supportFragmentManager
        var tr:FragmentTransaction = fmanager.beginTransaction()
        tr.add(R.id.frame_layout,mainFragment).commit()
    }
}
