package com.nothingspecial.mvicourse.ui.main.state

import com.nothingspecial.mvicourse.model.BlogPost
import com.nothingspecial.mvicourse.model.User

data class MainViewState(
    var blogPost: List<BlogPost>? = null,
    var user: User? = null
) {

}