package com.example.tututest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tututest.databinding.ListItemBinding
import com.example.tututest.model.Character

interface CharacterActions {

    fun openCharacter(char: Character)
}

class CharacterListAdapter(private val clickListener: CharacterActions): ListAdapter<Character,
        CharacterListAdapter.ItemViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Character>() {

        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.name == newItem.name
        }

    }

    class ItemViewHolder(
        private val binding: ListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(
            character: Character,
            clickListener: CharacterActions,
        ) {
            binding.character = character

            binding.listItem.setOnClickListener {
                clickListener.openCharacter(character)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(
           currentList[position],
           clickListener
        )
    }
}