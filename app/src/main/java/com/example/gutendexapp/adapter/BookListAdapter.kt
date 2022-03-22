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
import com.example.gutendexapp.model.Author
import com.example.gutendexapp.model.Result

class BookListAdapter(
    private val bookList: List<Result>,
    private var onBookClickListener: OnBookClickListener
) : RecyclerView.Adapter<BookListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_item_card_layout, parent, false)
        return ViewHolder(v, onBookClickListener)
    }

    override fun onBindViewHolder(holder: BookListAdapter.ViewHolder, position: Int) {
        holder.bind(bookList[position], onBookClickListener)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    inner class ViewHolder(itemView: View, private var onBookClickListener: OnBookClickListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val bookImage: ImageView = itemView.findViewById(R.id.imageView)
        private val bookName: TextView = itemView.findViewById(R.id.bookName)
        private val bookAuthor: TextView = itemView.findViewById(R.id.bookAuthor)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onBookClickListener.onBookClick(adapterPosition)
        }

        fun bind(bookData: Result, onBookClickListener: OnBookClickListener) {
            val requestOptions =
                RequestOptions().placeholder(R.drawable.book)
                    .error(R.drawable.book)
            Glide.with(itemView.context).applyDefaultRequestOptions(requestOptions)
                .load(bookData.formats.jpegFormat).into(bookImage)
            bookName.text = bookData.title
            bookAuthor.text = authorModifier(bookData.authors)
            this@BookListAdapter.onBookClickListener = onBookClickListener
        }

        private fun authorModifier(authors: List<Author>): String {
            return if (authors.count() == 0) {
                authors[0].name
            } else {
                var authorNames = ""
                authors.forEachIndexed { index, item ->
                    println("index = $index, item = $item ")
                    authorNames = if (index == 0) {
                        item.name
                    } else {
                        authorNames + ", " + item.name
                    }
                }

                authorNames
            }
        }
    }

    interface OnBookClickListener {
        fun onBookClick(position: Int)
    }
}