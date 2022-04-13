package com.example.tututest

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tututest.model.Character
import com.example.tututest.viewmodel.ApiStatus

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri){
            placeholder(R.drawable.loading_animation)
            error(R.drawable.error)
        }
    }
}

@BindingAdapter("ApiStatus")
fun bindStatus(imgView: ImageView, status: ApiStatus?) {
    when (status) {
        ApiStatus.ERROR -> {
            imgView.visibility = View.VISIBLE
            imgView.setImageResource(R.drawable.no_connection)
        }
        ApiStatus.LOADING -> {
            imgView.visibility = View.VISIBLE
            imgView.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.DONE -> {
            imgView.visibility = View.GONE
        }
    }
}

//@BindingAdapter("listData")
//fun bindRecyclerView(recyclerView: RecyclerView,
//                     data: MutableList<Character>) {
//    val adapter = recyclerView.adapter as CharacterListAdapter
//    (recyclerView.adapter as BindableAdapter).setData(data)
//    adapter.submitList(data)
//}
//
//interface BindableAdapter {
//    fun setData(data: MutableList<Character>)
//}