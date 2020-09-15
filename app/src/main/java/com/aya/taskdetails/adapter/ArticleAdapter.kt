package com.aya.taskdetails.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.aya.taskdetails.R
import com.aya.taskdetails.network.responseModel.data.Article
import com.squareup.picasso.Picasso
import java.util.*


class ArticleAdapter(val context: Context, article: List<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>()  {
    var article_list: List<Article> = ArrayList()
    var mContext: Context

    init {
        this.mContext = context
        this.article_list = article
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_item, parent, false)
        return ViewHolder(itemView)
    }

    fun setArticle(article_list: List<Article> ) {
        this.article_list =   article_list
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.article_item = article_list[position]
        holder.bind(holder.article_item!!)

    }


    override fun getItemCount(): Int {
        return article_list.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener  {


        var article_item: Article? = null
        var title_article: TextView?
        var Banner: ImageView?
        var auther_article: TextView?
        var date_article : TextView?
        var url :String =""
         init {
            Banner = view.findViewById(R.id.banner)
            title_article = view.findViewById(R.id.title_article)
            auther_article = view.findViewById(R.id.auther_article)
            date_article = view.findViewById(R.id.date_article)

            view.setOnClickListener(this)
        }

        fun bind(articleItem: Article?) {
            title_article?.text = articleItem!!.title
            auther_article?.text = articleItem!!.author
            date_article?.text = articleItem!!.publishedAt
            url = articleItem.url

            if (!articleItem.urlToImage.equals("") )
                Picasso.get()
                    .load(articleItem.urlToImage)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(Banner!!)
            else
                Picasso.get()
                    .load(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(Banner!!)
        }

        override fun onClick(view: View) {
            Log.i("article adapter","clicking.........." + url)
        }

    }

}