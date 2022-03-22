package com.example.gutendexapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gutendexapp.adapter.GenreAdapter
import com.example.gutendexapp.databinding.FragmentBookGenreBinding
import com.example.gutendexapp.viewmodel.BookGenreViewModel

class BookGenreFragment : Fragment(), GenreAdapter.OnGenreClickListener {
    private var _binding: FragmentBookGenreBinding? = null
    private val binding get() = _binding!!
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var viewModel: BookGenreViewModel

    companion object {
        const val TAG = "BookGenreFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BookGenreViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBookGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        genreAdapter = GenreAdapter(viewModel.createGenreSet(), this)
        binding.genreRecyclerView.apply {
            adapter = genreAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onGenreClick(position: Int) {
        val genre = viewModel.createGenreSet()[position].mTitle
        Log.d(TAG, "SelectedGenre : $genre")
        val action = BookGenreFragmentDirections.actionBookGenreFragmentToBookListFragment(genre)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}