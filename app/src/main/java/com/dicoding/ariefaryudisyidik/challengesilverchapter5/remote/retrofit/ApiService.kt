package com.dicoding.ariefaryudisyidik.challengesilverchapter5.remote.retrofit

import com.dicoding.ariefaryudisyidik.challengesilverchapter5.remote.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("movie/{id}")
    fun getMove(
        @Path("id") movieId: String
    ):Call<MovieResponse>
}