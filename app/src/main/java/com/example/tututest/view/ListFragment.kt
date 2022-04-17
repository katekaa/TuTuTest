package com.example.tututest.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tututest.CharacterActions
import com.example.tututest.CharacterListAdapter
import com.example.tututest.R
import com.example.tututest.databinding.ListBinding
import com.example.tututest.model.Character
import com.example.tututest.viewmodel.CharacterViewModel



class ListFragment: Fragment() {

    private lateinit var adapter: CharacterListAdapter
    private lateinit var binding: ListBinding
    private val viewModel: CharacterViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val layoutManager = LinearLayoutManager(requireContext())
        binding.viewModel = viewModel
        binding.recyclerView.layoutManager = layoutManager
        adapter = CharacterListAdapter(object : CharacterActions {

            override fun openCharacter(char: Character) {
                viewModel.onCharacterClicked(char)
                Log.d("!!", "kindaof trans new char is $char")
                findNavController()
                    .navigate(R.id.action_listFragment_to_detailFragment)
            }
        })

        binding.recyclerView.adapter = adapter
        viewModel.characters.observe(this, { adapter.submitList(it) })
        return binding.root
    }
}