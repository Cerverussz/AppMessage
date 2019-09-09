package com.danielleguizamon.appmessages.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danielleguizamon.appmessages.models.repository.PostsRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class FavoritePostsViewModel(private val postsRepository: PostsRepository) : ViewModel() {

    private val getAllFavoritePostsDBMutableLiveData: MutableLiveData<UIState> = MutableLiveData()

    fun getAllFavoritePostsDBLiveData(): LiveData<UIState> = getAllFavoritePostsDBMutableLiveData

    private val subscriptions = CompositeDisposable()

    fun getAllFavoriteDB() {
        subscriptions.add(postsRepository.getAllFavoritePost()
            .doOnSubscribe { getAllFavoritePostsDBMutableLiveData.postValue(UIState.Loading) }
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onNext = {
                    getAllFavoritePostsDBMutableLiveData.postValue(UIState.Success(it))
                },
                onComplete = {
                    Log.i(TAG, "--- Complete")
                },
                onError = {
                    getAllFavoritePostsDBMutableLiveData.postValue(
                        UIState.Error(
                            it.message ?: "Error"
                        )
                    )
                }
            ))
    }

    companion object {
        private const val TAG = "FavoritePostsViewModel"
    }
}