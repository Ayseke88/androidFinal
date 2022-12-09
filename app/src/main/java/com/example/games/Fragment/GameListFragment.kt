package com.example.games.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.games.databinding.FragmentGameListBinding

class GameListFragment : Fragment() {
    private lateinit var binding: FragmentGameListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGameListBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        const val ARG_MODE = "ARG_MODE"
        const val ARG_CATEGORY = "ARG_CATEGORY"
    }
}
