package com.aya.taskdetails.viewModel

import android.content.Context
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


    lateinit var adapter: ArticleAdapter
    //TAG to use later in logs
    var TAG = "FragmentListViewModel"

    init {
        this.navController = Navigation.findNavController(binding.root)
        article_list = listOf<Article>()
        //Init network layer
        apiClient = RetrofitClient.getInstance(mContext).api!!
        generalDataRepository = GeneralDataRepository(apiClient, mContext)
    }

    fun onClick(view: View?){

        }

    fun getArticle() {
        getDataJob = Coroutines.ioThenMain({ generalDataRepository.getHomeData() },
            {
                getArticle_( (it as HomeResponse).articles )
            })


    }

    fun getArticle_( ar_list :List<Article>) {
        getDataJob = Coroutines.ioThenMain({ generalDataRepository.getHomeData_2() },
            {
                _article.value = ar_list + (it as HomeResponse).articles
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
