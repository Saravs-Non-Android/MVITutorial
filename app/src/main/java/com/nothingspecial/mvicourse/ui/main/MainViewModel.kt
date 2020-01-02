package com.nothingspecial.mvicourse.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nothingspecial.mvicourse.ui.main.state.MainViewState

class MainViewModel : ViewModel() {

    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    /**
     * These lines are same as below method
     * fun observeViewState():LiveData<MainViewState>{
     *          return _viewState;
     * }
     */
    val viewState: LiveData<MainViewState>
        get() = _viewState

}