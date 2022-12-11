package com.example.games.view.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.games.data.model.Game
import com.example.games.data.model.GameEntity
import com.example.games.databinding.ItemGameCardBinding
import com.example.games.viewModel.GameViewModel

class GameAdapter(
    private val gameViewModel: GameViewModel,
    private val onGameItemClicked: (position: Int, games: List<Game>) -> Unit
): RecyclerView.Adapter<GameAdapter.GameViewHolder>() {
    var games: List<Game> = emptyList()

    class GameViewHolder(
        private val binding:ItemGameCardBinding,
        private val gameViewModel: GameViewModel,
        private val onGameItemClicked: (position: Int, games: List<Game>) -> Unit,
        private val games: List<Game>
    ): RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        @SuppressLint("SetTextI18n")
        fun bind(game: Game) {
            binding.apply {
                tvTitle.text = game.title
                tvReleaseDate.text = "Released in:" + game.release_date
                tvShortDescription.text = game.short_description
                btnAction.setOnClickListener {
                    if(gameViewModel.getGameById(game.id).value == null) {
                        gameViewModel.insertGame(GameEntity(game))
                        Toast.makeText(
                            binding.root.context , "Added to favourites", Toast.LENGTH_SHORT
                        ).show()
                    } else
                        Toast.makeText(
                            binding.root.context, "You already have this game in your list!", Toast.LENGTH_SHORT
                        ).show()
                }
            }
            Glide.with(binding.root)
                .load(game.thumbnail)
                .into(binding.imgPoster)
        }

        override fun onClick(p0: View?) {
            val position = absoluteAdapterPosition
            onGameItemClicked(position, games)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = ItemGameCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding, gameViewModel, onGameItemClicked, games)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]
        holder.bind(game)
    }

    override fun getItemCount() = games.size

}
