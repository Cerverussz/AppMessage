package com.danielleguizamon.appmessages.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danielleguizamon.appmessages.R
import com.danielleguizamon.appmessages.core.onClick
import com.danielleguizamon.appmessages.data.db.entities.Post
import kotlinx.android.synthetic.main.item_post.view.*

class PostsAdapter(val clickClosure: (Post) -> Unit) :
    RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    private var dataItems = arrayListOf<Post>()

    fun setData(posts: MutableList<Post>) {
        this.dataItems.clear()
        this.dataItems.addAll(posts)
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
        holder.bind(post, position)
        holder.bindClick(post)
    }

    override fun getItemId(position: Int): Long {
        return dataItems[position].id.toLong()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(post: Post, position: Int) {
            itemView.titlePostTextView.text = post.title
            itemView.postTextView.text = post.body

            if (post.isFavorite != 0) {
                itemView.favoriteCheckBox.visibility = VISIBLE
                itemView.favoriteCheckBox.isChecked = true
            } else {
                itemView.favoriteCheckBox.visibility = GONE
                itemView.favoriteCheckBox.isChecked = false
            }

            if (post.isRead != 0) {
                itemView.readImageView.visibility = GONE
            } else {
                if (position > 19) {
                    itemView.readImageView.visibility = GONE
                } else {
                    itemView.readImageView.visibility = VISIBLE
                }
            }
        }

        fun bindClick(post: Post) {
            itemView.onClick {
                clickClosure(post)
            }
        }

    }
}