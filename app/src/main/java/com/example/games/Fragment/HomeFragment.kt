package com.example.games.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.games.R
import com.example.games.databinding.FragmentHomeBinding

class HomeFragment: Fragment(R.layout.fragment_home) {
private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view:View,savedInstanceState: Bundle?){
    super.onViewCreated(view, savedInstanceState)
binding.apply{
btnCategories.setOnClickListener{
findNavController().navigate(R.id.action_homeFragment_to_categoryListFragment)
}
btnGames.setOnClickListener{
findNavController().navigate(
R.id.action_homeFragment_to_gameListFragment, bundleOf(GameListFragment.ARG_MODE to 0)
)
}
}
    }
}