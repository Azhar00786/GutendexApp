package com.example.gutendexapp.fragments

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gutendexapp.R
import com.example.gutendexapp.adapter.BookListAdapter
import com.example.gutendexapp.databinding.FragmentBookListBinding
import com.example.gutendexapp.model.Result
import com.example.gutendexapp.viewmodel.BookListViewModel


class BookListFragment : Fragment(), BookListAdapter.OnBookClickListener {
    private val args: BookListFragmentArgs by navArgs()
    private var _binding: FragmentBookListBinding? = null
    private val binding get() = _binding!!
    private lateinit var genre: String
    private lateinit var viewModel: BookListViewModel
    private lateinit var bookListAdapter: BookListAdapter
    private lateinit var bookList: List<Result>

    companion object {
        const val TAG = "BookListFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BookListViewModel::class.java)
        genre = args.genre
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            if (isEnabled) {
                findNavController().navigate(R.id.action_bookListFragment_to_bookGenreFragment)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBookListBinding.inflate(inflater, container, false)
        initAppBar()
        displayBooksOnGenre()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getBooksByCategory(genre)
        viewModel.bookList.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.progressBar.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                bookList = it
                initRecyclerView()
            }
        })
    }

    private fun initRecyclerView() {
        bookListAdapter = BookListAdapter(bookList, this)
        binding.recyclerView.apply {
            adapter = bookListAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }

    private fun initAppBar() {
        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationIcon(R.drawable.ic_back)
        binding.toolbar.setTitleTextColor(resources.getColor(R.color.app_primarycolor))
        binding.toolbar.title = genre
        binding.toolbar.setNavigationOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_bookListFragment_to_bookGenreFragment)
        })
    }

    private fun displayBooksOnGenre() {
        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(changedText: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (changedText != null) {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    viewModel.searchBooksByHint(genre, changedText.toString())
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    override fun onBookClick(position: Int) {
        val path = viewModel.formatChecker(bookList[position])
        if (path != null) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(path)
            try {
                requireContext().startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Log.d(TAG, "ERROR onBookClick() : ${e.printStackTrace()}")
                Toast.makeText(requireContext(), "ERROR opening browser", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(requireContext(), "No suitable format available", Toast.LENGTH_LONG)
                .show()
        }
    }
}