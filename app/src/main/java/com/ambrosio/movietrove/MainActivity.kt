package com.ambrosio.movietrove

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.ambrosio.movietrove.models.Movie
import com.ambrosio.movietrove.ui.FavoritesFragment
import com.ambrosio.movietrove.ui.HomeFragment
import com.ambrosio.movietrove.ui.movieDetails.MovieDetailsActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), HomeFragment.TouchActionDelegate {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupHomeFragment()
        val navView: BottomNavigationView = this.findViewById<BottomNavigationView>(R.id.navigationView)
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                setupHomeFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorites -> {
                setupFavoritesFragment()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun setupHomeFragment() {
        val fragment  = HomeFragment.newInstance()
        replaceFragment(fragment)
    }

    private fun setupFavoritesFragment() {
        val fragment  = FavoritesFragment.newInstance()
        replaceFragment(fragment)
    }

    private fun launchMovieDetailActivity(movie: Movie){
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.apply {
            putExtra(MOVIE_INTENT_NAME, movie)
        }
        startActivity(intent)
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentHolder, fragment)
            .commit()
    }

    override fun onMovieClicked(movie: Movie) {
        launchMovieDetailActivity(movie)
    }

    companion object{
        const val MOVIE_INTENT_NAME = "Movie"
    }

}