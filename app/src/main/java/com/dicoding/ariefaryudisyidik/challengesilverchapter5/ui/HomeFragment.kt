package com.dicoding.ariefaryudisyidik.challengesilverchapter5.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.ariefaryudisyidik.challengesilverchapter5.R
import com.dicoding.ariefaryudisyidik.challengesilverchapter5.databinding.FragmentHomeBinding
import com.dicoding.ariefaryudisyidik.challengesilverchapter5.remote.adapter.MovieAdapter
import com.dicoding.ariefaryudisyidik.challengesilverchapter5.remote.response.MovieResponse
import com.dicoding.ariefaryudisyidik.challengesilverchapter5.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val TAG = "HomeFragment"
        private const val API_KEY = "3e15ac9cb3114f2f303febd749ac0cf2"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchData()
        binding.ibProfile.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
    }

    private fun fetchData() {
        val client = ApiConfig.getApiService().getMovie(API_KEY)
        client.enqueue(object : retrofit2.Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                val responseBody = response.body()
                if (responseBody != null) {
                    val result = responseBody.results
                    val movieAdapter = MovieAdapter(result)
                    binding.rvMovies.setHasFixedSize(true)
                    binding.rvMovies.layoutManager = LinearLayoutManager(activity)
                    binding.rvMovies.adapter = movieAdapter
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}