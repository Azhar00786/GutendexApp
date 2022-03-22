package com.example.gutendexapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gutendexapp.model.Books
import com.example.gutendexapp.model.Result
import com.example.gutendexapp.network.ApiConnectorSingelton
import com.example.gutendexapp.network.ApiServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookListViewModel : ViewModel() {
    companion object {
        const val TAG = "BookListViewModel"
    }

    var bookList: MutableLiveData<List<Result>> = MutableLiveData()

    private fun getApiConnector(): ApiServiceInterface {
        val retrofitInstance = ApiConnectorSingelton.getRetrofitInstance()
        return retrofitInstance.create(ApiServiceInterface::class.java)
    }

    fun getBooksByCategory(genre: String) {
        val call = getApiConnector().getBooksByCategory(genre)
        call.enqueue(object : Callback<Books> {
            override fun onResponse(call: Call<Books>, response: Response<Books>) {
                bookList.value = response.body()?.results
                Log.d(TAG, "getBooksByCategory onResponse:  ${response.body()} ")
            }

            override fun onFailure(call: Call<Books>, t: Throwable) {
                Log.d(TAG, "getBooksByCategory onFailure: ${t.cause} || ${t.stackTrace}")
                bookList.value = null
            }
        })
    }

    fun searchBooksByHint(genre: String, searchText: String) {
        val call = getApiConnector().getBooksBySearch(genre, searchText)
        call.enqueue(object : Callback<Books> {
            override fun onResponse(call: Call<Books>, response: Response<Books>) {
                bookList.value = response.body()?.results
                Log.d(TAG, "getBooksBySearch onResponse:  ${response.body()} ")
            }

            override fun onFailure(call: Call<Books>, t: Throwable) {
                Log.d(TAG, "getBooksBySearch onFailure: ${t.cause} || ${t.stackTrace}")
                bookList.value = null
            }
        })
    }

    fun formatChecker(book: Result): String? {
        return when {
            book.formats.textHtmlCharsetIso88591Format != null -> {
                book.formats.textHtmlCharsetIso88591Format
            }
            book.formats.pdfFormat != null -> {
                book.formats.pdfFormat
            }
            book.formats.textPlainFormat != null -> {
                book.formats.textPlainFormat
            }
            else -> null
        }
    }
}
