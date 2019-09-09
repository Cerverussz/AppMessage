package com.danielleguizamon.appmessages.view.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.danielleguizamon.appmessages.R
import com.danielleguizamon.appmessages.data.db.entities.Post
import com.danielleguizamon.appmessages.view.adapters.FavoritePostAdapter
import com.danielleguizamon.appmessages.viewmodels.FavoritePostsViewModel
import com.danielleguizamon.appmessages.viewmodels.UIState
import kotlinx.android.synthetic.main.empty_view.*
import kotlinx.android.synthetic.main.fragment_favorite_posts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritePostsFragment : Fragment() {

    private val favoritePostsViewModel: FavoritePostsViewModel by viewModel()

    private lateinit var favoritePostAdapter: FavoritePostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        setupHandler()
        favoritePostsViewModel.getAllFavoriteDB()
    }

    private fun initUI() {
        favoritePostAdapter = FavoritePostAdapter()

        favoritePostsRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity!!, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = favoritePostAdapter
        }
    }

    private fun setupHandler() {
        favoritePostsViewModel.getAllFavoritePostsDBLiveData().observe(this, Observer { status ->
            when (status) {
                is UIState.Loading -> {
                    Log.i(TAG, "--- Loading...")
                }
                is UIState.Success<*> -> {
                    val data = status.data as MutableList<Post>
                    if (data.isNotEmpty()) {
                        favoritePostAdapter.setData(data)
                    } else {
                        includeEmptyView.visibility = VISIBLE
                        loadingTextView.text = getString(R.string.message_list_empty)
                    }
                }
                is UIState.Error -> {
                    includeEmptyView.visibility = VISIBLE
                    loadingTextView.text = getString(R.string.message_list_empty)
                    Log.i(TAG, "--- ${status.message}")
                }
            }
        })
    }

    companion object {
        private const val TAG = "FavoritePostsFragment"
    }
}
