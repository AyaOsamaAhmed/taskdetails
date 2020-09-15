package com.aya.taskdetails.viewModel.viewModelFactory


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aya.taskdetails.databinding.FragmentDetailsBinding
import com.aya.taskdetails.databinding.FragmentListBinding
import com.aya.taskdetails.viewModel.FragmentDetailsViewModel
import com.aya.taskdetails.viewModel.FragmentListViewModel

@Suppress("UNCHECKED_CAST")
class FragmentDetailsViewModelFactory(
    private val mContext: Context,
    private val binding: FragmentDetailsBinding
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FragmentDetailsViewModel(
            mContext,
            binding
        ) as T
    }
}