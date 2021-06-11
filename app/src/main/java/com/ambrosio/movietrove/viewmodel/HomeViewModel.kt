package com.ambrosio.movietrove.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ambrosio.movietrove.foundations.ApplicationScope
import com.ambrosio.movietrove.models.IMovieModel
import com.ambrosio.movietrove.models.Movie
import com.ambrosio.movietrove.models.RecyclerList
import com.ambrosio.movietrove.network.API_KEY
import com.ambrosio.movietrove.network.RetroInstance
import com.ambrosio.movietrove.network.Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import toothpick.Toothpick
import javax.inject.Inject

class HomeViewModel: ViewModel() {

    @Inject
    lateinit var model: IMovieModel

    private var nowPlayingLiveData : MutableLiveData<RecyclerList> = MutableLiveData()
    private var popularLiveData : MutableLiveData<RecyclerList> = MutableLiveData()

    init {
        Toothpick.inject(this, ApplicationScope.scope)
    }

    fun getNowPlayingListObserver(): MutableLiveData<RecyclerList> {
        return nowPlayingLiveData
    }

    fun getPopularListObserver(): MutableLiveData<RecyclerList> {
        return popularLiveData
    }

    fun fetchNowPlaying() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstance().create(Service::class.java)
            val response  = retroInstance.getNowPlaying(
                key = API_KEY,
                page = 1)
            val movies: List<Movie>? = response.results
            if(!movies.isNullOrEmpty())
                GlobalScope.launch {
                    model?.let {
                        it.addMovies(movies){ success ->
                            println("Saving movie models = $success")
                        }
                    }
                }
            nowPlayingLiveData.postValue(RecyclerList(movies as ArrayList<Movie>))
        }
    }

    fun fetchPopular() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstance().create(Service::class.java)
            val response  = retroInstance.getPopular(
                key = API_KEY,
                page = 1)
            popularLiveData.postValue(response.results?.let { RecyclerList(it) })
        }
    }
}