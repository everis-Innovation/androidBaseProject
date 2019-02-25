package com.base.viper.ui.privatezone.bindingdata

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.base.viper.R
import com.base.viper.data.network.model.Post
import kotlinx.android.synthetic.main.item_list_private.view.*

class SimpleRecyclerAdapter(private val context: Context, private val posts: List<Post>) : RecyclerView.Adapter<SimpleRecyclerAdapter.PostHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        return PostHolder(LayoutInflater.from(context).inflate(R.layout.item_list_private, parent, false))
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.bind(posts[position])
    }

    class PostHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post) {
            itemView.titleView.text = post.title
            itemView.contentView.text = post.content
        }
    }
}