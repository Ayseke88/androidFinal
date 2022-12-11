package com.example.games.view.Fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.games.data.model.GameEntity
import com.example.games.data.source.local.GameLocalRepository
import com.example.games.data.source.local.room.GameDatabase
import com.example.games.databinding.FragmentFavouritesBinding
import com.example.games.view.Adapter.FavouriteGamesAdapter
import com.example.games.viewModel.FavouritesViewModel

class FavouritesFragment : Fragment() {
    private lateinit var binding: FragmentFavouritesBinding
    private lateinit var favouritesViewModel: FavouritesViewModel
    private lateinit var rvFavGamesAdapter: FavouriteGamesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        provideViewModel()
        observeFavourites()
        viewInit()
    }

    private fun provideViewModel() {
        favouritesViewModel = FavouritesViewModel(
            GameLocalRepository.getInstance(GameDatabase.getInstance(requireActivity()).GameDao())
        )
    }

    private fun viewInit() {
        rvFavGamesAdapter = FavouriteGamesAdapter(favouritesViewModel) { position, games ->
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse(games[position].freetogame_profile_url)
            startActivity(openUrl)
        }
        binding.rvFavGames.apply {
            adapter = rvFavGamesAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun observeFavourites() {
        favouritesViewModel.getAllFavouriteGame().observe(viewLifecycleOwner) { games ->
            if (games != null) {
                rvFavGamesAdapter.games = games as MutableList<GameEntity>
                rvFavGamesAdapter.notifyDataSetChanged()
            }
        }
    }
}
