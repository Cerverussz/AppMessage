package com.danielleguizamon.appmessages.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danielleguizamon.appmessages.R
import com.danielleguizamon.appmessages.data.db.entities.Post
import kotlinx.android.synthetic.main.item_post.view.*

class FavoritePostAdapter : RecyclerView.Adapter<FavoritePostAdapter.ViewHolder>() {

    private val dataItems = mutableListOf<Post>()

    fun setData(posts: MutableList<Post>) {
        dataItems.clear()
        dataItems.addAll(posts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return dataItems.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = dataItems[position]
        holder.bind(post)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post) {
            itemView.titlePostTextView.text = post.title
            itemView.postTextView.text = post.body

            itemView.favoriteCheckBox.visibility = View.VISIBLE
            itemView.favoriteCheckBox.isChecked = true
            itemView.readImageView.visibility = View.GONE
        }
    }
}