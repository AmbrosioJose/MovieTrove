package com.ambrosio.movietrove.ui.movieDetails

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ambrosio.movietrove.MainActivity
import com.ambrosio.movietrove.R
import com.ambrosio.movietrove.models.Movie

class MovieDetailsActivity : AppCompatActivity() {
    lateinit var movie: Movie
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        intent?.getParcelableExtra<Movie>(MainActivity.MOVIE_INTENT_NAME).run{
            movie = this?: Movie()
            createFragment(MovieDetailsFragment.newInstance(movie))
        }

        supportActionBar?.title = ""
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(R.menu.menu_favorite, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.saveItem -> {
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun createFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentHolder, fragment)
            .commit()
    }
}