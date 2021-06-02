package com.ambrosio.movietrove

import android.app.Application

class TroveApplication : Application(){

    companion object {
        lateinit var instance: Application
    }

    init {
        instance = this
    }
}