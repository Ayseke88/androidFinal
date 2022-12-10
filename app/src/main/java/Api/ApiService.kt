package Api

import android.telecom.Call
import androidx.room.Query
import model.Game
import retrofit2.http.GET

interface ApiService {
    @GET("games/")
    fun getAllGames(): Call<List<Game>>

    @GET("games/")
    fun getGamesByCategory(
        @Query("category") category: String
    ): Call<List<Game>>
}