package com.aya.taskdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.aya.taskdetails.databinding.FragmentDetailsBinding
import com.aya.taskdetails.network.responseModel.data.Article
import com.aya.taskdetails.util.DrawerLocker
import com.aya.taskdetails.viewModel.FragmentDetailsViewModel
import com.aya.taskdetails.viewModel.viewModelFactory.FragmentDetailsViewModelFactory

class FragmentDetails   : Fragment() , View.OnClickListener {

     var article : Article = Article()
    companion object {
        fun newInstance() = FragmentDetails()
    }

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: FragmentDetailsViewModel
    private lateinit var factory: FragmentDetailsViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // recieve data from bundle
        arguments?.getString("author", "")?.let {
            article.author = it
        }
        arguments?.getString("title", "")?.let {
            article.title = it
        }
        arguments?.getString("description", "")?.let {
            article.description = it
        }
        arguments?.getString("urlToImage", "")?.let {
            article.urlToImage = it
        }
        arguments?.getString("url", "")?.let {
            article.url = it
        }
        arguments?.getString("publishedAt", "")?.let {
            article.publishedAt = it
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        factory =
            FragmentDetailsViewModelFactory(
               requireActivity(),
                binding
            )
        viewModel = ViewModelProviders.of(this, factory).get(FragmentDetailsViewModel::class.java)

        //close drawer
        (activity as DrawerLocker?)!!.setDrawerEnabled(false)
         // get details article
        viewModel.setData(article)
        // click button open website
        binding.openWebsite.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        viewModel.onClick(view, article.url)

    }
}