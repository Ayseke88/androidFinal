package com.example.games.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.games.data.model.GameEntity

@Dao
interface GameDao {
@Query("SELECT * FROM favourite_games")
fun getAllFavGames(): List<GameEntity>
@Query("SELECT * FROM favourite_games WHERE id=:id")
fun getGameById(id:Int): GameEntity?
@Insert
fun insertGame(game: GameEntity)
@Delete
fun deleteGame(game: GameEntity)
}