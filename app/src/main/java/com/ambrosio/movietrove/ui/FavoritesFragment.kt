package com.ambrosio.movietrove.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ambrosio.movietrove.MainActivity
import com.ambrosio.movietrove.R
import com.ambrosio.movietrove.adapter.RecyclerViewAdapter
import com.ambrosio.movietrove.models.Movie
import org.w3c.dom.Text


class FavoritesFragment : Fragment() {

    private lateinit var favoritesAdapter : RecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_favorites, container, false)

        return view



    }

    private fun initViewModel(view: View) {

    }

    private fun initViewModel() {

    }
    companion object {

        @JvmStatic
        fun newInstance() = FavoritesFragment()
    }
}