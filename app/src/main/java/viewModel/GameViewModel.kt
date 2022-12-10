package com.example.games.viewModel

import androidx.lifecycle.ViewModel
import com.example.games.data.model.GameEntity
import com.example.games.data.source.local.GameLocalRepository
import com.example.games.data.source.remote.GameRemoteRepository

class GameViewModel(
    private val gameRepository: GameRemoteRepository,
    private val gameLocalRepository: GameLocalRepository
): ViewModel() {
    fun getAllGames() = gameRepository.getAllGames()

    fun getGameById(id: Int) = gameLocalRepository.getGameById(id)

    fun insertGame(game: GameEntity) {
        gameLocalRepository.insertGame(game)
    }

    fun deleteGame(game: GameEntity) {
        gameLocalRepository.deleteGame(game)
    }
}