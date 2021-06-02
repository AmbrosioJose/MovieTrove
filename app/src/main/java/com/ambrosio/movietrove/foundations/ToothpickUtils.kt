package com.ambrosio.movietrove.foundations

//import com.ambrosio.movietrove.database.MovieLocalModel
import com.ambrosio.movietrove.models.IMovieModel
import toothpick.Scope
import toothpick.Toothpick
import toothpick.config.Module

object ApplicationScope {
    val scope: Scope = Toothpick.openScope(this).apply{
        installModules(ApplicationModule)
    }

}

object ApplicationModule: Module(){
    init{
//        bind(IMovieModel::class.java).toInstance(MovieLocalModel())
    }
}