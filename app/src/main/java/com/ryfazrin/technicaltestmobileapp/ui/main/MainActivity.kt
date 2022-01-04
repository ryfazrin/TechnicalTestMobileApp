package com.ryfazrin.technicaltestmobileapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ryfazrin.technicaltestmobileapp.data.DetailUserResponse
import com.ryfazrin.technicaltestmobileapp.data.PostsResponseItem
import com.ryfazrin.technicaltestmobileapp.databinding.ActivityMainBinding
import com.ryfazrin.technicaltestmobileapp.ui.detailpost.DetailPostActivity
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var listPost: ArrayList<PostsResponseItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(MainViewModel::class.java)

        listPost = ArrayList<PostsResponseItem>()

        mainViewModel.posts.observe(this, { posts ->

            if (listPost.isEmpty()) {
                setPostData(posts, mainViewModel.listUser)
            }

            val scrollDirectionDown = 1 // Scroll down is +1, up is -1.
            var currentListSize = 0

            binding.rvPosts.addOnScrollListener( object : RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if (!recyclerView.canScrollVertically(scrollDirectionDown)
                        && newState == RecyclerView.SCROLL_STATE_IDLE
                    ) {
                        val listSizeAfterLoading = recyclerView.layoutManager!!.itemCount
                        // List has more item.
                        if (currentListSize != listSizeAfterLoading) {
                            currentListSize = listSizeAfterLoading
                            showLoading(true)
                            // Get more posts.
                            if (listPost.isNotEmpty()) {
                                setPostData(posts, mainViewModel.listUser, listPost.size)
                            }
                            showLoading(false)
                        }
                        else { // List comes limit.
//                            showToastMessageShort("No more items.")
                            showError(true)
                        }
                    }
                }
            })
        })

        val layoutManager = LinearLayoutManager(this)
        binding.rvPosts.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvPosts.addItemDecoration(itemDecoration)

        mainViewModel.isLoading.observe(this, {
            showLoading(it)
        })

        mainViewModel.isMessage.observe(this, {
            showError(it)
        })
    }

    private fun setPostData(posts: List<PostsResponseItem>, users: ArrayList<DetailUserResponse>, listLength: Int = 0) {
//        listPost.clear()

        var counter = listLength
//
//        if (listPost.size != null) {
//            counter = listPost.size
//        }

        while (counter < counter + 5){
            listPost.add(posts[counter])
            counter++
        }

//        listPost.addAll(posts)

        val adapter = MainPostsAdapter(listPost, users)

        binding.rvPosts.adapter = adapter

        adapter.setOnItemClickCallback(object : MainPostsAdapter.OnItemCLickCallback {
            override fun onItemClicked(post: PostsResponseItem, user: DetailUserResponse) {
                showSelectedPost(post, user)
            }
        })
    }

//    private fun loadMore(posts: List<PostsResponseItem>, users: ArrayList<DetailUserResponse>) {
//        var counter = listPost.size
//        while (counter < counter + 5) {
//            listPost.add(posts[counter])
//            counter++
//        }
//
//
//        val adapter = MainPostsAdapter(listPost, users)
//
//        binding.rvPosts.adapter = adapter
//
//        adapter.setOnItemClickCallback(object : MainPostsAdapter.OnItemCLickCallback {
//            override fun onItemClicked(post: PostsResponseItem, user: DetailUserResponse) {
//                showSelectedPost(post, user)
//            }
//        })
//    }

    private fun showSelectedPost(post: PostsResponseItem, user: DetailUserResponse) {
        val moveDetailIntent = Intent(this@MainActivity, DetailPostActivity::class.java)
        moveDetailIntent.putExtra(DetailPostActivity.EXTRA_POST, post)
        moveDetailIntent.putExtra(DetailPostActivity.EXTRA_DETAIL_USER, user)

        startActivity(moveDetailIntent)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showError(isMessage: Boolean) {
        binding.errorMessage.visibility = if (isMessage) View.VISIBLE else View.GONE
    }
}