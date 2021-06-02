package com.ambrosio.movietrove.ui.movieDetails

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ambrosio.movietrove.R
import com.ambrosio.movietrove.models.Movie
import com.ambrosio.movietrove.network.BASE_BACKDROP_URL
import com.ambrosio.movietrove.network.BASE_POSTER_URL
import com.bumptech.glide.Glide
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import toothpick.Toothpick
import javax.inject.Inject

class MovieDetailsFragment(val movie: Movie) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view =  inflater.inflate(R.layout.fragment_movie_detail, container, false)

        val titleTv = view.findViewById<TextView>(R.id.tvTitle)
        titleTv.text = movie.title

        val overviewTv = view.findViewById<TextView>(R.id.tvOverview)
        overviewTv.text = movie.overview

        val ratingTv = view.findViewById<TextView>(R.id.tvRating)
        ratingTv.text = movie.voteAverage.toString()

        val imageView = view.findViewById<ImageView>(R.id.imageThumb)
        val url  = "$BASE_BACKDROP_URL${movie.backdropPath}"
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        // TODO: Rename and change types and number of parameters
        fun newInstance(movie: Movie): MovieDetailsFragment {
            return MovieDetailsFragment(movie)
        }
    }

}