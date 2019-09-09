package com.danielleguizamon.appmessages.view.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.danielleguizamon.appmessages.R
import com.danielleguizamon.appmessages.data.db.entities.InfoUser
import com.danielleguizamon.appmessages.data.db.entities.Post
import com.danielleguizamon.appmessages.data.remote.api.ApiService
import com.danielleguizamon.appmessages.viewmodels.PostDetailViewModel
import com.danielleguizamon.appmessages.viewmodels.UIState
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val postDetailViewModel: PostDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = DetailFragmentArgs.fromBundle(arguments!!).post
        initUI(bundle)
        setupHandler()
        readPost(bundle)
        setupListener(bundle)
    }

    override fun onResume() {
        super.onResume()
        //TODO: send post read
    }

    private fun initUI(post: Post) {
        post.apply {
            titlePostTextView.text = title
            postTextView.text = body
            favoriteCheckBox.isChecked = isFavorite != 0
            postDetailViewModel.getUser(userId)
        }
    }

    private fun setupListener(post: Post) {
        favoriteCheckBox.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                postDetailViewModel.addFavoritePost(
                    Post(
                        id = post.id,
                        userId = post.userId,
                        title = post.title,
                        body = post.body,
                        isRead = 1,
                        isFavorite = 1
                    )
                )
            } else {
                postDetailViewModel.deleteFavoritePost(
                    Post(
                        id = post.id,
                        userId = post.userId,
                        title = post.title,
                        body = post.body,
                        isRead = 1,
                        isFavorite = 0
                    )
                )
            }
        }
    }

    private fun setupHandler() {
        postDetailViewModel.getUserListDBLiveData().observe(this, Observer { status ->
            when (status) {
                is UIState.Loading -> {
                    Log.i(TAG, "--- Loading...")
                }
                is UIState.Success<*> -> {
                    val infoUser = status.data as InfoUser
                    infoUser.apply {
                        nameTextView.text = name
                        phoneTextView.text = phone
                        emailTextView.text = email
                    }
                }
                is UIState.Error -> {
                    Log.i(TAG, "--- ${status.message}")
                }
            }
        })

        postDetailViewModel.readPostLiveData().observe(this, Observer { status ->
            when (status) {
                is UIState.Loading -> {
                    Log.i(TAG, "--- Loading...")
                }
                is UIState.Success<*> -> {
                    val readPost = status.data as Boolean
                    Log.i(TAG, "--- Success")
                }
                is UIState.Error -> {
                    Log.i(TAG, "--- ${status.message}")
                }
            }
        })

        postDetailViewModel.addFavoritePostLiveData().observe(this, Observer { status ->
            when (status) {
                is UIState.Loading -> {
                    Log.i(TAG, "--- Loading...")
                }
                is UIState.Success<*> -> {
                    val add = status.data as Boolean
                    Log.i(TAG, "--- Success Add")
                }
                is UIState.Error -> {
                    Log.i(TAG, "--- ${status.message}")
                }
            }
        })

        postDetailViewModel.deleteFavoritePostLiveData().observe(this, Observer { status ->
            when (status) {
                is UIState.Loading -> {
                    Log.i(TAG, "--- Loading...")
                }
                is UIState.Success<*> -> {
                    val delete = status.data as Boolean
                    Log.i(TAG, "--- Success Delete")
                }
                is UIState.Error -> {
                    Log.i(TAG, "--- ${status.message}")
                }
            }
        })
    }

    private fun readPost(post: Post) {
        if (post.isRead != 1) {
            post.apply {
                postDetailViewModel.readPost(
                    Post(
                        id = id,
                        userId = userId,
                        title = title,
                        body = body,
                        isRead = 1,
                        isFavorite = isFavorite
                    )
                )
            }
        }
    }

    companion object {
        const val TAG = "DetailFragment"
    }
}
