package com.ryfazrin.technicaltestmobileapp.ui.detailpost

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ryfazrin.technicaltestmobileapp.data.CommentsResponseItem
import com.ryfazrin.technicaltestmobileapp.databinding.ItemRowCommentBinding

class CommentsAdapter(private val listComment: ArrayList<CommentsResponseItem>) : RecyclerView.Adapter<CommentsAdapter.ListViewHolder>()  {

    inner class ListViewHolder(private val binding: ItemRowCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: CommentsResponseItem) {
            with(binding) {
                tvPostCommentName.text = comment.name
                tvPostCommentBody.text = comment.body
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listComment[position])
    }

    override fun getItemCount(): Int = listComment.size
}