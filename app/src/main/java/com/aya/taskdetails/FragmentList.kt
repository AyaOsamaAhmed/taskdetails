package com.aya.taskdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aya.taskdetails.databinding.FragmentListBinding
import com.aya.taskdetails.network.responseModel.data.Article
import com.aya.taskdetails.util.DrawerLocker
import com.aya.taskdetails.viewModel.FragmentListViewModel
import com.aya.taskdetails.viewModel.viewModelFactory.FragmentListViewModelFactory

class FragmentList   : Fragment() , View.OnClickListener {


    companion object {
        fun newInstance() = FragmentList()
    }

    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: FragmentListViewModel
    private lateinit var factory: FragmentListViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        factory = FragmentListViewModelFactory(requireActivity(), binding)
        viewModel = ViewModelProvider(this, factory).get(FragmentListViewModel::class.java)
        // init Recycler
        viewModel.articleRecyclerViewInit(binding.articalRecyclerview)
        //open drawer
        (activity as DrawerLocker?)!!.setDrawerEnabled(true)
        // get article
        viewModel.getArticle()
        // put data in adapter
        viewModel.article.observe(viewLifecycleOwner, Observer {
            viewModel.adapter.setArticle(it)
            notifyDataChanged(it)
        })

    }
    private fun notifyDataChanged(article_list: List<Article>) {
        if (!article_list.isEmpty())
            swithVisablityView(binding.noArticleLayout, binding.articalRecyclerview)
        else
            swithVisablityView(binding.articalRecyclerview, binding.noArticleLayout)
    }

    //to hide first view parameter and display the 2nd one
    fun swithVisablityView(v1: View, v2: View) {
        v1.visibility = View.GONE
        v2.visibility = View.VISIBLE
    }

    override fun onClick(view: View?) {
        viewModel.onClick(view)

    }
}