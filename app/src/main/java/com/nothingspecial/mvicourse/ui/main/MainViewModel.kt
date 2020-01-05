package com.nothingspecial.mvicourse.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.nothingspecial.mvicourse.model.BlogPost
import com.nothingspecial.mvicourse.model.User
import com.nothingspecial.mvicourse.ui.main.state.MainStateEvent
import com.nothingspecial.mvicourse.ui.main.state.MainViewState
import com.nothingspecial.mvicourse.util.AbsentLiveData

class MainViewModel : ViewModel() {

    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData();
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    /**
     * These lines are same as below method
     * fun observeViewState():LiveData<MainViewState>{
     *          return _viewState;
     * }
     */
    val viewState: LiveData<MainViewState>
        get() = _viewState

    val dataState: LiveData<MainViewState> = Transformations
        .switchMap(_stateEvent) {
            it?.let {
                handleStateEvent(it)
            }
        }

    private fun handleStateEvent(stateEvent: MainStateEvent): LiveData<MainViewState> {
        when (stateEvent) {
            is MainStateEvent.GetBlogPostsEvent -> {
                return object : LiveData<MainViewState>() {
                    override fun onActive() {
                        super.onActive()
                        val blogList: ArrayList<BlogPost> = ArrayList()
                        blogList.add(
                            BlogPost(
                                pk = 0,
                                title = "Vancouver PNE 2019",
                                body = "Here is Jess and I at the Vancouver PNE. We ate a lot of food.",
                                image = "https://cdn.open-api.xyz/open-api-static/static-blog-images/image8.jpg"
                            )
                        )
                        blogList.add(
                            BlogPost(
                                pk = 1,
                                title = "Ready for a Walk",
                                body = "Here I am at the park with my dogs Kiba and Maizy. Maizy is the smaller one and Kiba is the larger one.",
                                image = "https://cdn.open-api.xyz/open-api-static/static-blog-images/image2.jpg"
                            )
                        )
                        value = MainViewState(
                            blogPost = blogList
                        )
                    }
                }
            }

            is MainStateEvent.GetUserEvent -> {
                return object : LiveData<MainViewState>() {
                    override fun onActive() {
                        super.onActive()
                        val user = User(
                            email = "mitch@tabian.ca",
                            userName = "mitch",
                            image = "https://cdn.open-api.xyz/open-api-static/static-random-images/logo_1080_1080.png"
                        )
                        value = MainViewState(
                            user = user
                        )
                    }
                }
            }

            is MainStateEvent.None -> {
                return AbsentLiveData.create();
            }
        }
    }

    fun setBlogListData(blogPosts: List<BlogPost>) {
        val update = getCurrentViewStateOrNew()
        update.blogPost = blogPosts
        _viewState.value = update

    }

    fun setUser(user: User) {
        val update = getCurrentViewStateOrNew()
        update.user = user
        _viewState.value = update
    }

    fun getCurrentViewStateOrNew(): MainViewState {
        val value = viewState.value?.let {
            it
        } ?: MainViewState()
        return value
    }

    fun setStateEvent(event: MainStateEvent) {
        _stateEvent.value = event
    }

}