package com.danielleguizamon.appmessages.viewmodels

import androidx.lifecycle.ViewModel
import com.danielleguizamon.appmessages.models.repository.PostsRepository

class FavoritePostsViewModel(private val postsRepository: PostsRepository) : ViewModel() {
}