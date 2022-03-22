package com.example.gutendexapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.gutendexapp.R
import com.example.gutendexapp.model.GenreItem

class BookGenreViewModel : ViewModel() {
    fun createGenreSet(): ArrayList<GenreItem> {
        val list = ArrayList<GenreItem>()
        list.add(
            GenreItem(R.drawable.ic_fiction, "FICTION")
        )
        list.add(
            GenreItem(R.drawable.ic_drama, "DRAMA")
        )
        list.add(
            GenreItem(R.drawable.ic_humour, "HUMOR")
        )
        list.add(
            GenreItem(R.drawable.ic_politics, "POLITICS")
        )
        list.add(
            GenreItem(R.drawable.ic_philosophy, "PHILOSOPHY")
        )
        list.add(
            GenreItem(R.drawable.ic_history, "HISTORY")
        )
        list.add(
            GenreItem(R.drawable.ic_adventure, "ADVENTURE")
        )
        return list
    }
}