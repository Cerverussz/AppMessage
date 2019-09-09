package com.danielleguizamon.appmessages.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danielleguizamon.appmessages.data.db.entities.Post
import com.danielleguizamon.appmessages.models.repository.PostsRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class PostDetailViewModel(private val postsRepository: PostsRepository) : ViewModel() {

    private val getUserDBMutableLiveData: MutableLiveData<UIState> = MutableLiveData()
    private val readPostMutableLiveData: MutableLiveData<UIState> = MutableLiveData()
    private val addFavoritePostMutableLiveData: MutableLiveData<UIState> = MutableLiveData()
    private val deleteFavoritePostMutableLiveData: MutableLiveData<UIState> = MutableLiveData()


    fun getUserListDBLiveData(): LiveData<UIState> = getUserDBMutableLiveData
    fun readPostLiveData(): LiveData<UIState> = readPostMutableLiveData
    fun addFavoritePostLiveData(): LiveData<UIState> = addFavoritePostMutableLiveData
    fun deleteFavoritePostLiveData(): LiveData<UIState> = deleteFavoritePostMutableLiveData

    private val subscriptions = CompositeDisposable()

    fun getUser(id: Int) {
        subscriptions.add(
            postsRepository.getUserDB(id)
                .doOnSubscribe { getUserDBMutableLiveData.postValue(UIState.Loading) }
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onSuccess = {
                        getUserDBMutableLiveData.postValue(UIState.Success(it))
                    },
                    onError = {
                        getUserDBMutableLiveData.postValue(
                            UIState.Error(
                                it.message
                                    ?: "Error"
                            )
                        )
                    }
                )
        )
    }

    fun readPost(post: Post) {
        subscriptions.add(
            postsRepository.readPost(post).doOnSubscribe { readPostMutableLiveData.postValue(UIState.Loading) }
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onComplete = {
                        readPostMutableLiveData.postValue(UIState.Success(true))
                    },
                    onError = {
                        readPostMutableLiveData.postValue(
                            UIState.Error(
                                it.message ?: "Error"
                            )
                        )
                    }
                )
        )
    }

    fun addFavoritePost(post: Post) {
        subscriptions.add(
            postsRepository.readPost(post).doOnSubscribe {
                addFavoritePostMutableLiveData.postValue(
                    UIState.Loading
                )
            }
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onComplete = {
                        addFavoritePostMutableLiveData.postValue(UIState.Success(true))
                    },
                    onError = {
                        addFavoritePostMutableLiveData.postValue(
                            UIState.Error(
                                it.message ?: "Error"
                            )
                        )
                    }
                )
        )
    }

    fun deleteFavoritePost(post: Post) {
        subscriptions.add(
            postsRepository.readPost(post).doOnSubscribe {
                deleteFavoritePostMutableLiveData.postValue(
                    UIState.Loading
                )
            }
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onComplete = {
                        deleteFavoritePostMutableLiveData.postValue(UIState.Success(true))
                    },
                    onError = {
                        deleteFavoritePostMutableLiveData.postValue(
                            UIState.Error(
                                it.message ?: "Error"
                            )
                        )
                    }
                )
        )
    }
}