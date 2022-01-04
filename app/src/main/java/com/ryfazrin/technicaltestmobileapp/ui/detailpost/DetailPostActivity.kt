package com.ryfazrin.technicaltestmobileapp.ui.detailpost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ryfazrin.technicaltestmobileapp.data.CommentsResponseItem
import com.ryfazrin.technicaltestmobileapp.data.DetailUserResponse
import com.ryfazrin.technicaltestmobileapp.data.PostsResponseItem
import com.ryfazrin.technicaltestmobileapp.databinding.ActivityDetailPostBinding
import com.ryfazrin.technicaltestmobileapp.ui.detailpost.DetailPostActivity.Companion.EXTRA_DETAIL_USER
import com.ryfazrin.technicaltestmobileapp.ui.detailuser.DetailUserActivity

class DetailPostActivity : AppCompatActivity() {

    private lateinit var commentsViewModel: CommentsViewModel
    private lateinit var binding: ActivityDetailPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val getPost: PostsResponseItem = intent.getParcelableExtra<PostsResponseItem>(EXTRA_POST) as PostsResponseItem
        val getUser: DetailUserResponse = intent.getParcelableExtra<DetailUserResponse>(
            EXTRA_DETAIL_USER) as DetailUserResponse

        commentsViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(CommentsViewModel::class.java)

        try {
            commentsViewModel.getComments(getPost.id)

            binding.tvDetailPostTitle.text = getPost.title
            binding.tvDetailPostName.text = getUser.name
            binding.tvDetailPostBody.text = getPost.body

            commentsViewModel.comments.observe(this, { comments ->
                setCommentsData(comments)
            })

            commentsViewModel.isLoading.observe(this, {
                showLoading(it)
            })

            val layoutManager = LinearLayoutManager(this)
            binding.rvComments.layoutManager = layoutManager

            binding.tvDetailPostName.setOnClickListener {
                val moveDetailUserIntent = Intent(this@DetailPostActivity, DetailUserActivity::class.java)
                moveDetailUserIntent.putExtra(DetailUserActivity.EXTRA_DETAIL_USER, getUser)
                startActivity(moveDetailUserIntent)
            }

        } catch (e: Exception) {
            Log.e("DetailPostActivity", "onCreate: ${e.message}")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setCommentsData(comments: List<CommentsResponseItem>) {

        val listComment = ArrayList<CommentsResponseItem>()
        listComment.clear()

        listComment.addAll(comments)

        val adapter = CommentsAdapter(listComment)

        binding.rvComments.adapter = adapter
    }


    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        var EXTRA_POST = "extra_post"
        var EXTRA_DETAIL_USER = "extra_detail_user"
    }
}