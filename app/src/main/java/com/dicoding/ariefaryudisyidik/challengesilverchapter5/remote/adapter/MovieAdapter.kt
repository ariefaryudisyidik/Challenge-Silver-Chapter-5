package com.dicoding.ariefaryudisyidik.challengesilverchapter5.remote.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.ariefaryudisyidik.challengesilverchapter5.R
import com.dicoding.ariefaryudisyidik.challengesilverchapter5.databinding.ItemMovieBinding
import com.dicoding.ariefaryudisyidik.challengesilverchapter5.remote.response.Results
import com.dicoding.ariefaryudisyidik.challengesilverchapter5.ui.HomeFragmentDirections

class MovieAdapter(private val listMovie: List<Results>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    companion object{
        const val posterBaseUrl = "https://image.tmdb.org/t/p/original/"
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Results)
    }

    class ViewHolder(var binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movieResponse = listMovie[position]
        val poster = posterBaseUrl + movieResponse.posterPath
        val title = movieResponse.title
        val releaseDate = movieResponse.releaseDate
        val averageRating = movieResponse.voteAverage.toString()

        Log.d("adapter", "onBindViewHolder: $poster")
        holder.binding.apply {
            Glide.with(holder.itemView.context).load(poster).into(ivPoster)
            tvTitle.text = title
            tvReleaseDate.text = releaseDate
            tvAverageRating.text = averageRating
            tvReadMore.setOnClickListener {
                onItemClickCallback.onItemClicked(listMovie[holder.adapterPosition])
            }
        }
    }

    override fun getItemCount() = listMovie.size
}