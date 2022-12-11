package com.example.games.viewModel

import androidx.lifecycle.ViewModel
import com.example.games.data.model.GameEntity
import com.example.games.data.source.local.GameLocalRepository

class FavouritesViewModel(private val gameLocalRepository: GameLocalRepository) : ViewModel() {

    fun getAllFavouriteGame() = gameLocalRepository.getFavouriteGames()

    fun insertGame(game: GameEntity) {
        gameLocalRepository.insertGame(game)
    }

    fun deleteGame(game: GameEntity) {
        gameLocalRepository.deleteGame(game)
    }
}