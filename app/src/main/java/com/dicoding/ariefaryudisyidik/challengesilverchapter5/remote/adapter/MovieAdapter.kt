package com.dicoding.ariefaryudisyidik.challengesilverchapter5.remote.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.ariefaryudisyidik.challengesilverchapter5.databinding.ItemMovieBinding
import com.dicoding.ariefaryudisyidik.challengesilverchapter5.remote.response.MovieResponse
import com.dicoding.ariefaryudisyidik.challengesilverchapter5.remote.response.ResultsItem

class MovieAdapter(private val listMovie: List<ResultsItem>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val imageBaseUrl = "https://image.tmdb.org/t/p/original/"
        val movieResponse = listMovie[position]
        val poster = imageBaseUrl + listMovie[position].posterPath
        val title = movieResponse.title
        val releaseDate = movieResponse.releaseDate
        val averageRating = movieResponse.voteAverage.toString()

        Glide.with(holder.itemView.context).load(poster).into(holder.binding.ivPoster)
        Log.d("adapter", "onBindViewHolder: $poster")
        holder.binding.tvTitle.text = title
        holder.binding.tvReleaseDate.text = releaseDate
        holder.binding.tvAverageRating.text = averageRating
    }

    override fun getItemCount() = listMovie.size
}