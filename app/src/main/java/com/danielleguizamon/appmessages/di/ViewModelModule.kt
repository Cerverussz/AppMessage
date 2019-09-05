package com.danielleguizamon.appmessages.di

import com.danielleguizamon.appmessages.viewmodels.FavoritePostsViewModel
import com.danielleguizamon.appmessages.viewmodels.PostDetailViewModel
import com.danielleguizamon.appmessages.viewmodels.PostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { FavoritePostsViewModel(get()) }
    viewModel { PostDetailViewModel(get()) }
    viewModel { PostsViewModel(get()) }
}