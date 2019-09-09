package com.danielleguizamon.appmessages.view.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.danielleguizamon.appmessages.R
import com.danielleguizamon.appmessages.data.db.entities.Post
import com.danielleguizamon.appmessages.view.adapters.PostsAdapter
import com.danielleguizamon.appmessages.view.widget.SwipeToDeleteCallback
import com.danielleguizamon.appmessages.viewmodels.PostsViewModel
import com.danielleguizamon.appmessages.viewmodels.UIState
import kotlinx.android.synthetic.main.empty_view.*
import kotlinx.android.synthetic.main.fragment_posts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("UNCHECKED_CAST")
class PostsFragment : Fragment() {

    private val postsViewModel: PostsViewModel by viewModel()

    private lateinit var postsAdapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postsViewModel.getUsersListDB()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        setupToolbar()
        setupHandler()
    }

    private fun initUI() {
        postsAdapter = PostsAdapter { post ->
            val action = PostsFragmentDirections.actionPostsFragmentToDetailFragment(post)
            findNavController().navigate(action)
        }

        postsAdapter.setHasStableIds(true)
        postsListRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity!!, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(activity!!, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
            adapter = postsAdapter
        }

        val swipeHandler = object : SwipeToDeleteCallback(activity!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                deletePost(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(postsListRecyclerView)
    }

    private fun deletePost(position: Int) {
        postsAdapter.getData().forEachIndexed { index, post ->
            if (index == position) {
                postsViewModel.deletePost(post.id)
            }
        }
    }

    private fun setupToolbar() {
        toolbar.setTitle(R.string.app_name)
        toolbar.inflateMenu(R.menu.menu)

        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_main_favorite -> {
                    findNavController().navigate(R.id.action_postsFragment_to_favoritePostsFragment)
                }
                R.id.menu_main_refresh -> {
                    postsViewModel.getPostsAPI()
                }

                R.id.menu_main_remove -> {
                    postsViewModel.deleteAllPost()
                }
            }
            true
        }
    }

    private fun setupHandler() {
        postsViewModel.getPostsListDBLiveData().observe(this, Observer { status ->
            when (status) {
                is UIState.Loading -> {
                    Log.i(TAG, "--- Loading...")
                }
                is UIState.Success<*> -> {
                    val data = status.data as MutableList<Post>
                    Log.i(TAG, "--- Success DB")
                    if (data.count() != 0) {
                        postsAdapter.setData(data)
                    } else {
                        includeEmptyView.visibility = View.VISIBLE
                        loadingTextView.text = getString(R.string.message_list_empty)
                        postsViewModel.getPostsAPI()
                    }
                }
                is UIState.Error -> {
                    includeEmptyView.visibility = View.VISIBLE
                    loadingTextView.text = getString(R.string.message_connection_error)
                    Log.i(TAG, "--- ${status.message}")
                }
            }

        })

        postsViewModel.getPostsListAPILiveData().observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { status ->
                when (status) {
                    is UIState.Loading -> {
                        Log.i(TAG, "--- Loading...")
                    }
                    is UIState.Success<*> -> {
                        val data = status.data as MutableList<Post>
                        Log.i(TAG, "--- Success...")
                        if (data.count() != 0) {
                            includeEmptyView.visibility = View.GONE
                            postsAdapter.setData(data)
                            postsViewModel.getUsers()
                        } else {
                            includeEmptyView.visibility = View.VISIBLE
                            loadingTextView.text = getString(R.string.message_list_empty)
                        }
                    }
                    is UIState.Error -> {
                        includeEmptyView.visibility = View.VISIBLE
                        loadingTextView.text = getString(R.string.message_connection_error)
                        Log.i(TAG, "--- ${status.message}")
                    }
                }
            }
        })

        postsViewModel.isUsersListAPIMutableLiveData().observe(this, Observer { status ->
            when (status) {
                is UIState.Loading -> {
                    Log.i(TAG, "--- Loading...")
                }
                is UIState.Success<*> -> {
                    val data = status.data as Boolean
                    Log.i(TAG, "--- Insert Users $data")
                }
                is UIState.Error -> {
                    Log.i(TAG, "--- ${status.message}")
                }
            }
        })

        postsViewModel.deleteAllPostDBLiveData().observe(this, Observer { status ->
            when (status) {
                is UIState.Loading -> {
                    Log.i(TAG, "--- Loading...")
                }
                is UIState.Success<*> -> {
                    val data = status.data as Boolean
                    Log.i(TAG, "--- Success")
                }
                is UIState.Error -> {
                    Log.i(TAG, "--- ${status.message}")
                }
            }
        })

        postsViewModel.deletePostDBLiveData().observe(this, Observer { status ->
            when (status) {
                is UIState.Loading -> {
                    Log.i(TAG, "--- Loading...")
                }
                is UIState.Success<*> -> {
                    val data = status.data as Boolean
                    Log.i(TAG, "--- Success Delete")
                }
                is UIState.Error -> {
                    Log.i(TAG, "--- ${status.message}")
                }
            }
        })
    }

    companion object {
        const val TAG = "PostsFragment"
    }
}
