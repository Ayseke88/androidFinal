package com.example.games.data.source.remote.Api

import android.telecom.Call
import androidx.room.Query
import com.example.games.data.model.Game
import retrofit2.http.GET

interface ApiService {
    @GET("games/")
    fun getAllGames(): retrofit2.Call<List<Game>>


}