package com.example.myapplication.mvp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.mvp.interfaces.NewsContract
import com.example.myapplication.R

class NewsListFragment : Fragment(), NewsContract.BaseView {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news_list,container,false)
        return view
    }

    override fun noInternetConnection() {

    }

    override fun onReloadSuccess() {

    }

    override fun onReloadError() {

    }

}