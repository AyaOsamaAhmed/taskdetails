package com.aya.taskdetails.viewModel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.coroutines.Job
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.taskdetails.R
import com.aya.taskdetails.adapter.ArticleAdapter
import com.aya.taskdetails.databinding.FragmentDetailsBinding
import com.aya.taskdetails.databinding.FragmentListBinding
import com.aya.taskdetails.network.ApiInterface
import com.aya.taskdetails.network.Coroutines
import com.aya.taskdetails.network.RetrofitClient
import com.aya.taskdetails.network.repository.GeneralDataRepository
import com.aya.taskdetails.network.responseModel.HomeResponse
import com.aya.taskdetails.network.responseModel.data.Article
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*


class FragmentDetailsViewModel(
    private val mContext: Context,
    private val binding: FragmentDetailsBinding
) : ViewModel() {

   lateinit var fragmentDetailsBinding:FragmentDetailsBinding
    var context:Context
    //TAG to use later in logs
    var TAG = "FragmentDetailsViewModel"

    init {
        context = mContext
        fragmentDetailsBinding = binding
    }
    fun setData(article: Article) {
        fragmentDetailsBinding.titleArticle.setText(article.title)
        fragmentDetailsBinding.autherArticle.setText(article.author)
       fragmentDetailsBinding.detailsArticle.setText(article.description)

        // set Image url in design
        if (!article.urlToImage.equals("") )
            Picasso.get()
                .load(article.urlToImage)
                .placeholder(R.drawable.ic_launcher_background)
                .into(fragmentDetailsBinding.banner!!)
        else
            Picasso.get()
                .load(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_background)
                .into(fragmentDetailsBinding.banner!!)

        // change formate of date
        var dateArticle :String = article.publishedAt.substring(0,10)
        val dateFormat =  SimpleDateFormat("yyyy-MM-dd")
        val sourceDate : Date
        sourceDate = dateFormat.parse(dateArticle)
        val format = SimpleDateFormat("MMM dd, yyyy")
        val date: String = format.format(sourceDate)
        // set date in design
        fragmentDetailsBinding.dateArticle.setText(date)

    }

    fun onClick(view: View? , url :String ){
        Log.i("fragment Details",""+url)
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        mContext.startActivity(browserIntent)
        }



}
