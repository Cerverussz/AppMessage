package com.danielleguizamon.appmessages.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danielleguizamon.appmessages.models.repository.PostsRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class PostsViewModel(private val postsRepository: PostsRepository) : ViewModel() {


    private val postsListDBMutableLiveData: MutableLiveData<UIState> = MutableLiveData()
    private val postsListAPIMutableLiveData: MutableLiveData<Event<UIState>> = MutableLiveData()
    private val isUsersListAPIMutableLiveData: MutableLiveData<UIState> = MutableLiveData()
    private val deleteAllPostDBMutableLiveData: MutableLiveData<UIState> = MutableLiveData()
    private val deletePostDBMutableLiveData: MutableLiveData<UIState> = MutableLiveData()


    fun getPostsListDBLiveData(): LiveData<UIState> = postsListDBMutableLiveData
    fun getPostsListAPILiveData(): LiveData<Event<UIState>> = postsListAPIMutableLiveData
    fun isUsersListAPIMutableLiveData(): LiveData<UIState> = isUsersListAPIMutableLiveData
    fun deleteAllPostDBLiveData(): LiveData<UIState> = deleteAllPostDBMutableLiveData
    fun deletePostDBLiveData(): LiveData<UIState> = deletePostDBMutableLiveData

    private val subscriptions = CompositeDisposable()


    fun getPostsAPI() {
        subscriptions.add(
            postsRepository.getPosts()
                .doOnSubscribe {
                    postsListAPIMutableLiveData.postValue(Event(UIState.Loading))
                }.subscribeOn(Schedulers.io())
                .subscribeBy(
                    onNext = {
                        postsListAPIMutableLiveData.postValue(Event(UIState.Success(it)))
                    },
                    onError = {
                        postsListAPIMutableLiveData.postValue(
                            Event(
                                UIState.Error(
                                    it.message
                                        ?: "Error"
                                )
                            )
                        )
                    }
                )
        )
    }

    fun getUsersListDB() {
        subscriptions.add(
            postsRepository.getPostsDB()
                .doOnSubscribe {
                    postsListDBMutableLiveData.postValue(UIState.Loading)
                }.subscribeOn(Schedulers.io())
                .subscribeBy(
                    onNext = {
                        postsListDBMutableLiveData.postValue(UIState.Success(it))
                    },
                    onError = {
                        postsListDBMutableLiveData.postValue(
                            UIState.Error(
                                it.message
                                    ?: "Error"
                            )
                        )
                    }
                )
        )
    }

    fun getUsers() {
        subscriptions.add(
            postsRepository.getUsers().doOnSubscribe {
                isUsersListAPIMutableLiveData.postValue(UIState.Loading)
            }.subscribeOn(Schedulers.io())
                .subscribeBy(
                    onComplete = {
                        isUsersListAPIMutableLiveData.postValue(UIState.Success(true))
                    },
                    onError = {
                        isUsersListAPIMutableLiveData.postValue(
                            UIState.Error(
                                it.message
                                    ?: "Error"
                            )
                        )
                    }
                )
        )
    }

    fun deleteAllPost() {
        subscriptions.add(
            postsRepository.deleteAllPost()
                .doOnSubscribe {
                    deleteAllPostDBMutableLiveData.postValue(UIState.Loading)
                }.subscribeOn(Schedulers.io())
                .subscribeBy(
                    onComplete = {
                        deleteAllPostDBMutableLiveData.postValue(UIState.Success(true))
                    },
                    onError = {
                        deleteAllPostDBMutableLiveData.postValue(
                            UIState.Error(
                                it.message
                                    ?: "Error"
                            )
                        )
                    }
                )
        )
    }

    fun deletePost(id: Int) {
        subscriptions.add(
            postsRepository.deletePost(id)
                .doOnSubscribe {
                    deletePostDBMutableLiveData.postValue(UIState.Loading)
                }.subscribeOn(Schedulers.io())
                .subscribeBy(
                    onComplete = {
                        deletePostDBMutableLiveData.postValue(UIState.Success(true))
                    },
                    onError = {
                        deletePostDBMutableLiveData.postValue(
                            UIState.Error(
                                it.message
                                    ?: "Error"
                            )
                        )
                    }
                )
        )
    }
}