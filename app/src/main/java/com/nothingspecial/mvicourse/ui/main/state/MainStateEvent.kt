package com.nothingspecial.mvicourse.ui.main.state

sealed class MainStateEvent {

    class GetBlogPost : MainStateEvent()

    class GetUserEvent(
        val userId: String
    ) : MainStateEvent()

    class None : MainStateEvent()
}