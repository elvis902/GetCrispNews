package com.example.getcrispnews.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.getcrispnews.DetailActivity
import com.example.getcrispnews.R
import com.example.getcrispnews.utils.Articles
import kotlinx.android.synthetic.main.item_layout.view.*

class NewsAdapter(val context: Context, private val list: List<Articles>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.item_layout,parent, false)
        return NewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = list[position]
        holder.newsTitle.setText(article.title)
        holder.newsDescription.setText(article.description)
        val imageUrl = article.urlToImage
        Glide.with(context).load(imageUrl).into(holder.newsImage)
        holder.itemView.setOnClickListener {
            //Toast.makeText(context, list[position].title, Toast.LENGTH_SHORT).show()
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("URL", list[position].url)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsImage : ImageView = itemView.news_image
        val newsTitle : TextView = itemView.news_title
        val newsDescription : TextView = itemView.news_description

    }
}
