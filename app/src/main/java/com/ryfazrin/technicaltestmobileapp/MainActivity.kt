package com.ryfazrin.technicaltestmobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ryfazrin.technicaltestmobileapp.data.PostsResponseItem
import com.ryfazrin.technicaltestmobileapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(MainViewModel::class.java)
        mainViewModel.posts.observe(this, { post ->
            setPostData(post)
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

    private fun setPostData(posts: List<PostsResponseItem>) {
        val listPost = ArrayList<PostsResponseItem>()
        listPost.clear()

        listPost.addAll(posts)

        val adapter = MainPostsAdapter(listPost)
        binding.rvPosts.adapter = adapter

        adapter.setOnItemClickCallback(object : MainPostsAdapter.OnItemCLickCallback {
            override fun onItemClicked(data: PostsResponseItem) {
                showSelectedPost(data)
            }
        })
    }

    private fun showSelectedPost(post: PostsResponseItem) {
        val moveDetalIntent = Intent(this@MainActivity, )
        moveDetalIntent.putExtra()

        startActivity()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showError(isMessage: Boolean) {
        binding.errorMessage.visibility = if (isMessage) View.VISIBLE else View.GONE
    }
}