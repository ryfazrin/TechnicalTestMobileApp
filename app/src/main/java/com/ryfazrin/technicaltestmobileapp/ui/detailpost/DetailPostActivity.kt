package com.ryfazrin.technicaltestmobileapp.ui.detailpost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ryfazrin.technicaltestmobileapp.R
import com.ryfazrin.technicaltestmobileapp.data.DetailUserResponse
import com.ryfazrin.technicaltestmobileapp.data.PostsResponseItem
import com.ryfazrin.technicaltestmobileapp.databinding.ActivityDetailPostBinding

class DetailPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getPost: PostsResponseItem = intent.getParcelableExtra<PostsResponseItem>(EXTRA_POST) as PostsResponseItem
        val getUser: DetailUserResponse = intent.getParcelableExtra<DetailUserResponse>(
            EXTRA_DETAIL_USER) as DetailUserResponse

        binding.tvDetailPostTitle.text = getPost.title
        binding.tvDetailPostName.text = getUser.name
        binding.tvDetailPostBody.text = getPost.body
    }

    companion object {
        var EXTRA_POST = "extra_post"
        var EXTRA_DETAIL_USER = "extra_detail_user"
    }
}