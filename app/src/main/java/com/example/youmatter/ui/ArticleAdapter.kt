package com.example.youmatter.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youmatter.R
import com.example.youmatter.data.model.articleLists.Data
import android.content.Context;

class ArticleAdapter(private val articleList: ArrayList<Data>?, private val mContext: Context): RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.article_title)
        val descriptionTextView: TextView = itemView.findViewById(R.id.article_description)
        val thumbnailImageView: ImageView= itemView.findViewById(R.id.article_thumbnail)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ArticleViewHolder,
        position: Int
    ) {
        val article = articleList?.get(position)
        holder.titleTextView.text = article?.title
        holder.descriptionTextView.text = article?.description
        Glide.with(mContext)
            .load(article?.coverImage)
            .into(holder.thumbnailImageView)
    }

    override fun getItemCount(): Int = articleList?.size!!

}