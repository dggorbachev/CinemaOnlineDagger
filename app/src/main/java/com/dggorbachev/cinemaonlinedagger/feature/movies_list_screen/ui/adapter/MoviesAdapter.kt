package com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dggorbachev.cinemaonlinedagger.R
import com.dggorbachev.cinemaonlinedagger.base.common.Constants.IMG_URL
import com.dggorbachev.cinemaonlinedagger.base.utils.setThrottledClickListener
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.domain.model.Movie
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.ui.MoviesFragmentDirections
import kotlinx.coroutines.currentCoroutineContext

class MoviesAdapter(
    private var moviesList: List<Movie>
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val backImage: ImageView
        val rating: TextView
        val releaseDate: TextView
        val rvGenres: RecyclerView

        init {
            this.title = view.findViewById<TextView>(R.id.tvTitle)
            this.backImage = view.findViewById<ImageView>(R.id.ivBackdrop)
            this.rating = view.findViewById<TextView>(R.id.tvRating)
            this.releaseDate = view.findViewById<TextView>(R.id.tvReleaseDate)
            this.rvGenres = view.findViewById(R.id.rvGenres)
        }
    }

    lateinit var view: View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curMovie = moviesList[position]

        holder.apply {
            title.text = curMovie.title
            rating.text = getRating(position)
            releaseDate.text = curMovie.releaseDate

            Glide.with(view)
                .load(IMG_URL + curMovie.backdropPath)
                .centerCrop()
                .into(holder.backImage)
        }

        val layoutManager =
            LinearLayoutManager(holder.rvGenres.context, LinearLayoutManager.HORIZONTAL, false)
        layoutManager.initialPrefetchItemCount = moviesList[position].genreIds.size

        val genresAdapter = GenresAdapter(genreIds = moviesList[position].genreIds)
        holder.rvGenres.layoutManager = layoutManager
        holder.rvGenres.adapter = genresAdapter
        holder.rvGenres.setRecycledViewPool(viewPool)

        holder.backImage.setThrottledClickListener { mView ->
            val direction = MoviesFragmentDirections
                .actionMoviesFragmentToMovieDetailsFragment(curMovie)
            mView.findNavController().navigate(direction)
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun updateFilms(newArticles: List<Movie>) {
        moviesList = newArticles
        notifyDataSetChanged()
    }

    private fun getRating(position: Int) =
        (((moviesList[position].voteAverage * 10).toInt()).toString() + "%")
}