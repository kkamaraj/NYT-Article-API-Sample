package com.capsule.android.healthylifestyle.ui.main.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capsule.android.healthylifestyle.R
import com.capsule.android.healthylifestyle.model.Article
import com.capsule.android.healthylifestyle.ui.main.adapter.ArticleAdapter.ArticleViewHolder

class ArticleAdapter(
    private var data: List<Article> = emptyList()
) : RecyclerView.Adapter<ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item_view, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = data[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int = data.size

    fun setData(articles: List<Article>) {
        data = articles
        notifyDataSetChanged()
    }

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val articleTitle: TextView = itemView.findViewById(R.id.article_title)
        private val articleDesc: TextView = itemView.findViewById(R.id.article_description)

        fun bind(article: Article) {
            articleTitle.text = article.headline.printHeadline
            articleDesc.text = article.snippet
            itemView.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW)
                browserIntent.data = Uri.parse(article.webUrl)
                itemView.context.startActivity(browserIntent)
            }
        }
    }
}