package com.example.tututest

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tututest.databinding.ListItemBinding
import com.example.tututest.model.Character
import android.widget.ArrayAdapter




//class ListAdapter(
////    private val characters: MutableList<Character>
//): RecyclerView.Adapter<ListAdapter.ItemViewHolder>() {

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


 lateinit var context: Context

    var characters = mutableListOf<Character>()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
            Log.d("??", "nv $newValue")
    }
//
//    override fun setData(data: MutableList<Character>) {
//        characters = data
//        notifyDataSetChanged()
//    }



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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListAdapter.ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        this.context = parent.context
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(
            currentList[position],
           clickListener
        )
        Log.d("??", "onBindViewHolder char is ${getItem(position)}")
    }




}