package com.ryfazrin.technicaltestmobileapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ryfazrin.technicaltestmobileapp.data.DetailUserResponse
import com.ryfazrin.technicaltestmobileapp.data.PostsResponseItem
import com.ryfazrin.technicaltestmobileapp.databinding.ItemRowPostBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@DelicateCoroutinesApi
class MainPostsAdapter(
    private val listPost: ArrayList<PostsResponseItem>,
    private val listUser: ArrayList<DetailUserResponse>
) :
    RecyclerView.Adapter<MainPostsAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemCLickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemCLickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(private val binding: ItemRowPostBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(posts: PostsResponseItem, user: DetailUserResponse) {
            with(binding) {
                tvPostTitle.text = posts.title
                tvPostBody.text = posts.body
                tvPostUsername.text = user.name
                tvPostUserCompany.text = user.company.name
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = ItemRowPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val jobListUser = GlobalScope.launch {
            listUser
        }

        runBlocking {
            jobListUser.join()
            holder.bind(listPost[position], listUser[position])

            holder.itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(listPost[holder.adapterPosition], listUser[holder.adapterPosition])
            }
        }
    }

    override fun getItemCount(): Int = listPost.size

    interface OnItemCLickCallback {
        fun onItemClicked(post: PostsResponseItem, user: DetailUserResponse)
    }
}