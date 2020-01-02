package com.nothingspecial.mvicourse.ui.main.state

import com.nothingspecial.mvicourse.model.BlogPost
import com.nothingspecial.mvicourse.model.User

data class MainViewState(
    val blogPost: List<BlogPost>? = null,
    val user: User? = null
) {

}