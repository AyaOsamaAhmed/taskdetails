package com.aya.taskdetails.viewModel

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.coroutines.Job
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.taskdetails.adapter.ArticleAdapter
import com.aya.taskdetails.databinding.FragmentListBinding
import com.aya.taskdetails.network.ApiInterface
import com.aya.taskdetails.network.Coroutines
import com.aya.taskdetails.network.RetrofitClient
import com.aya.taskdetails.network.repository.GeneralDataRepository
import com.aya.taskdetails.network.responseModel.HomeResponse
import com.aya.taskdetails.network.responseModel.data.Article


class FragmentListViewModel(
    private val mContext: Context,
    private val binding: FragmentListBinding
) : ViewModel() {
    lateinit var getDataJob: Job

     var generalDataRepository: GeneralDataRepository

    private val _article = MutableLiveData<List<Article>>()
    val article: LiveData<List<Article>> get() = _article

    var article_list : List<Article>

     var apiClient: ApiInterface

    private var navController: NavController? = null
    var context:Context

    lateinit var adapter: ArticleAdapter
    //TAG to use later in logs
    var TAG = "FragmentListViewModel"

    init {
        this.navController = Navigation.findNavController(binding.root)
        article_list = listOf<Article>()
        //Init network layer
        apiClient = RetrofitClient.getInstance(mContext).api!!
        generalDataRepository = GeneralDataRepository(apiClient, mContext)
        context = mContext
    }

    fun onClick(view: View?){

        }


    fun getArticle() {
        getDataJob = Coroutines.ioThenMain({
            try {
                generalDataRepository.getHomeData()
            } catch (e: Exception) {
                (mContext as Activity?)?.runOnUiThread {
                    Toast.makeText(mContext,"no internet connection",Toast.LENGTH_SHORT).show()
                }
                Log.e("getArticle", "exception handled" + (e.toString()))

            }
        },
            {
                try {
                    if (it is HomeResponse?)
                        getArticle_((it as HomeResponse)?.articles)
                    else {
                        //TODO Display Error
                    }
                } catch (e: Exception) {
                    Log.e("getArticle", "exception handled" + (e.toString()))
                }
            })
    }



    fun getArticle_( ar_list :List<Article>) {
        getDataJob = Coroutines.ioThenMain({
            try {
                generalDataRepository.getHomeData_2()
            }catch (e:Exception){
                (mContext as Activity?)?.runOnUiThread {
                    Toast.makeText(mContext,"no internet connection",Toast.LENGTH_SHORT).show()
                }
                Log.e("getArticle", "exception handled" + (e.toString()))
            }},
            {
                try{

                _article.value = ar_list + (it as HomeResponse).articles

            } catch (e:Exception){
                    Log.e("getArticle", "exception handled" + (e.toString()))
                }
            })
    }

    fun articleRecyclerViewInit(article_recyclerview: RecyclerView) {
        adapter = ArticleAdapter(mContext.applicationContext, ArrayList(),navController!!)
        val gridLayoutManager = GridLayoutManager(mContext, 1, GridLayoutManager.VERTICAL, false)
        article_recyclerview.layoutManager = gridLayoutManager
        article_recyclerview.setHasFixedSize(true)
        article_recyclerview.adapter = adapter
    }



    override fun onCleared() {
        super.onCleared()
        if (::getDataJob.isInitialized)
            getDataJob.cancel()
    }
}
