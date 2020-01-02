package com.nothingspecial.mvicourse.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.nothingspecial.mvicourse.ui.main.state.MainStateEvent
import com.nothingspecial.mvicourse.ui.main.state.MainViewState
import com.nothingspecial.mvicourse.util.AbsentLiveData

class MainViewModel : ViewModel() {

    private val _viewEvent: MutableLiveData<MainStateEvent> = MutableLiveData();
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    /**
     * These lines are same as below method
     * fun observeViewState():LiveData<MainViewState>{
     *          return _viewState;
     * }
     */
    val viewState: LiveData<MainViewState>
        get() = _viewState

    val dataState: LiveData<MainStateEvent> = Transformations
        .switchMap(_viewEvent) {
            it?.let {
                handleStateEvent(it)
            }
        }

    private fun handleStateEvent(stateEvent: MainStateEvent): LiveData<MainStateEvent> {
        when (stateEvent) {
            is MainStateEvent.GetBlogPost -> {
                return AbsentLiveData.create();
            }
            is MainStateEvent.GetUserEvent -> {
                return AbsentLiveData.create();
            }

            is MainStateEvent.None -> {
                return AbsentLiveData.create();
            }
        }

    }

}