package com.ambrosio.movietrove.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ambrosio.movietrove.R
import com.ambrosio.movietrove.adapter.RecyclerViewAdapter
import com.ambrosio.movietrove.models.Movie
import com.ambrosio.movietrove.models.RecyclerList
import com.ambrosio.movietrove.viewmodel.HomeViewModel


class HomeFragment : Fragment() {

    private lateinit var nowPlayingAdapter : RecyclerViewAdapter
    private lateinit var popularAdapter : RecyclerViewAdapter
    lateinit var touchActionDelegate: TouchActionDelegate

    override fun onAttach(context: Context){
        super.onAttach(context)
        context.let{
            if(context is TouchActionDelegate){
                touchActionDelegate = context
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_home, container, false).apply{
            initViewModel(this)
        }
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initViewModel(view)
        initViewModel()
    }

    private fun initViewModel(view: View) {
        val nowPlayingRV = view.findViewById<RecyclerView>(R.id.nowPlayingRV)
        nowPlayingRV.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        nowPlayingAdapter = RecyclerViewAdapter(touchActionDelegate = touchActionDelegate,)
        nowPlayingRV.adapter = nowPlayingAdapter


        val popularPlayingRV = view.findViewById<RecyclerView>(R.id.popularRV)
        popularPlayingRV.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        popularAdapter = RecyclerViewAdapter(touchActionDelegate = touchActionDelegate,)
        popularPlayingRV.adapter = popularAdapter


    }

    private fun initViewModel() {
        val viewModel  = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.getNowPlayingListObserver().observe(viewLifecycleOwner, Observer<RecyclerList> {
            if(it?.items != null) {
                println(it)
                nowPlayingAdapter.setUpdatedData(it.items)
            } else {
                Toast.makeText(activity, "Error in fetching data", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.getPopularListObserver().observe(viewLifecycleOwner, Observer<RecyclerList> {
            if(it?.items != null) {
                println("Got Popular data!!!")
                popularAdapter.setUpdatedData(it.items)
            } else {
                Toast.makeText(activity, "Error in fetching data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.fetchNowPlaying()
        viewModel.fetchPopular()
    }
    companion object {

        @JvmStatic
        fun newInstance() =
            HomeFragment()
    }

    interface TouchActionDelegate{
        abstract fun onMovieClicked(movie: Movie)
    }
}