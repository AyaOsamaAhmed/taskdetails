package com.aya.taskdetails.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.aya.taskdetails.R
import com.aya.taskdetails.network.responseModel.data.Article
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*


class ArticleAdapter(val context: Context, article: List<Article> , navController: NavController) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>()  {
    var article_list: List<Article> = ArrayList()
    var mContext: Context
    var navController:NavController? = null
    init {
        this.mContext = context
        this.article_list = article
        this.navController = navController
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
            url = articleItem.url
            // formate date
            var dateArticle :String = articleItem!!.publishedAt.substring(0,10)
            val dateFormat =  SimpleDateFormat("yyyy-MM-dd")
            val sourceDate :Date
            sourceDate = dateFormat.parse(dateArticle)
            val format = SimpleDateFormat("MMM dd, yyyy")
            val date: String = format.format(sourceDate)
            // put date in design
            date_article?.text = date

            // load url Image in design
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
            var bundle = Bundle()
            bundle.putString("title", article_item!!.title)
            bundle.putString("url", article_item!!.url)
            bundle.putString("author", article_item!!.author)
            bundle.putString("description", article_item!!.description)
            bundle.putString("publishedAt", article_item!!.publishedAt)
            bundle.putString("urlToImage", article_item!!.urlToImage)

            // move to fragment details
            navController!!.navigate(R.id.action_FragmentList_to_FragmentDetails,bundle)
        }

    }

}