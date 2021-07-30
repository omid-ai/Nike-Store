package com.example.nikestore.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nikestore.data.Comment
import com.example.nikestore.databinding.ItemCommentBinding

class CommentListAdapter: RecyclerView.Adapter<CommentListAdapter.ViewHolder>() {

    lateinit var binding: ItemCommentBinding

    inner class ViewHolder(binding: ItemCommentBinding): RecyclerView.ViewHolder(binding.root){
        fun bindComment(comment: Comment){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}