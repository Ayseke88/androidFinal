package com.example.games.view.Fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.games.data.source.local.GameLocalRepository
import com.example.games.data.source.local.room.GameDatabase
import com.example.games.data.source.remote.GameRemoteRepository
import com.example.games.databinding.FragmentGameListBinding
import com.example.games.viewModel.GameViewModel
import com.example.games.view.Adapter.GameAdapter

class GameListFragment : Fragment() {
    private lateinit var binding: FragmentGameListBinding
    private lateinit var gameViewModel: GameViewModel
    private lateinit var rvGamesAdapter: GameAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGameListBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        provideGameData()
        viewInit()
    }

    private fun provideGameData() {
        provideViewModel()
        observeGames()
    }

    private fun provideViewModel() {
        gameViewModel = GameViewModel(
            GameRemoteRepository.getInstance(),
            GameLocalRepository.getInstance(GameDatabase.getInstance(requireContext()).GameDao())
        )
    }

    private fun viewInit() {
        rvGamesAdapter = GameAdapter(gameViewModel) { position, games ->
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse(games[position].freetogame_profile_url)
            startActivity(openUrl)
        }
        binding.rvGames.apply {
            adapter = rvGamesAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun observeGames() {
        when (requireArguments().getInt(ARG_MODE)) {
            0 -> gameViewModel.getAllGames().observe(viewLifecycleOwner) { games ->
                if (games != null) {
                    rvGamesAdapter.games = games
                    rvGamesAdapter.notifyDataSetChanged()
                }
            }
        }
    }
    companion object {
        const val ARG_MODE = "ARG_MODE"
    }
}
