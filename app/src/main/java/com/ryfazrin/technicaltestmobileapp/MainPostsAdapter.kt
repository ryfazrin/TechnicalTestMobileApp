package com.ryfazrin.technicaltestmobileapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ryfazrin.technicaltestmobileapp.data.DetailUserResponse
import com.ryfazrin.technicaltestmobileapp.data.PostsResponseItem
import com.ryfazrin.technicaltestmobileapp.databinding.ItemRowPostBinding

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

        fun bind(posts: PostsResponseItem) {
//          fun bind(posts: PostsResponseItem) {
            with(binding) {
                tvPostTitle.text = posts.title
                tvPostBody.text = posts.body
//                tvPostUsername.text = user.name
//                tvPostUserCompany.text = user.company.name
//                tvPostUsername.text = posts.userId.toString()
            }
        }

        fun bindUser(user: DetailUserResponse) {
            with(binding) {
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
        holder.bindUser(listUser[position])
        holder.bind(listPost[position])
        Log.e("MainPostsAdapter", "listUser: ${listUser[0].name}")

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listPost[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listPost.size

    interface OnItemCLickCallback {
        fun onItemClicked(data: PostsResponseItem)
    }
}