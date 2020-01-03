package com.nothingspecial.mvicourse.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nothingspecial.mvicourse.R

class MainFragment : Fragment() {

    //Initialize View Model
    lateinit var viewModel: MainViewModel;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Initialize View Model
        viewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        setHasOptionsMenu(true)
        subscribeObservers();
    }

    fun subscribeObservers() {
        //Observe lifecyclerowner instead of activity
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            println("Debug : datastate ${dataState}")
            dataState.blogPost?.let {
                //set blog posts data
            }

            dataState.user?.let {

            }
        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            viewState.blogPost?.let {
                println("Debug : Setting blog posts to recyclerview")
            }

            viewState.user?.let {
                println("Debug : Setting user data ")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}