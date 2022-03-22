package com.example.gutendexapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.gutendexapp.R
import com.example.gutendexapp.model.GenreItem

class GenreAdapter(
    private val genreData: ArrayList<GenreItem>,
    private val onGenreClickListener: OnGenreClickListener
) : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_genre_card_layout, parent, false)
        return ViewHolder(v, onGenreClickListener)
    }

    override fun onBindViewHolder(holder: GenreAdapter.ViewHolder, position: Int) {
        holder.bind(genreData[position], onGenreClickListener)
    }

    override fun getItemCount(): Int {
        return genreData.size
    }

    inner class ViewHolder(itemView: View, private var onGenreClickListener: OnGenreClickListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var genreImage: ImageView = itemView.findViewById(R.id.genreImage)
        var genreName: TextView = itemView.findViewById(R.id.genreType)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            onGenreClickListener.onGenreClick(adapterPosition)
        }

        fun bind(genreItem: GenreItem, onGenreClickListener: OnGenreClickListener) {
            val requestOptions =
                RequestOptions().placeholder(R.drawable.ic_baseline_broken_image_24)
                    .error(R.drawable.ic_baseline_broken_image_24)
            Glide.with(itemView.context).applyDefaultRequestOptions(requestOptions)
                .load(genreItem.mIcon).into(genreImage)

            genreName.text = genreItem.mTitle
            this@ViewHolder.onGenreClickListener = onGenreClickListener
        }
    }

    interface OnGenreClickListener {
        fun onGenreClick(position: Int)
    }
}