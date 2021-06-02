package com.ambrosio.movietrove.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ambrosio.movietrove.R
import com.ambrosio.movietrove.models.Movie
import com.ambrosio.movietrove.network.BASE_POSTER_URL
import com.ambrosio.movietrove.ui.HomeFragment
import com.bumptech.glide.Glide

class RecyclerViewAdapter(
    val touchActionDelegate: HomeFragment.TouchActionDelegate
    ) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>(){

    var items = ArrayList<Movie>()

    fun setUpdatedData(items : ArrayList<Movie>) {
        this.items = items
        notifyDataSetChanged()
    }
    class MyViewHolder(view: View, touchActionDelegate: HomeFragment.TouchActionDelegate): RecyclerView.ViewHolder(view) {
        private val touchDelegate: HomeFragment.TouchActionDelegate = touchActionDelegate
        val imageThumb = view.findViewById<ImageView>(R.id.imageThumb)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)


        fun bind(data : Movie) {
            tvTitle.text = data.title

            val url  = "$BASE_POSTER_URL${data.posterPath}"
            println(url)
            Glide.with(imageThumb.context)
                .load(url)
                .into(imageThumb)

            itemView.setOnClickListener{ view ->
                touchDelegate.onMovieClicked(data)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MyViewHolder(view, touchActionDelegate)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items.get(position))

    }
}