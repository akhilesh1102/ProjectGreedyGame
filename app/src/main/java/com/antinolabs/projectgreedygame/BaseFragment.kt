package com.antinolabs.projectgreedygame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<T: ViewDataBinding,VM: AndroidViewModel> : Fragment() {

    protected lateinit var binding:T
    protected lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,getFragmentView(),
        container,false)
        viewModel=ViewModelProvider(this).get(getViewModel())
        return binding.root
    }
    abstract fun getFragmentView() :Int
    abstract fun getViewModel():Class<VM>
}