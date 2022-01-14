package com.example.shopapp.core.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.shopapp.core.viewmodel.BaseViewModel
import com.example.shopapp.domain.util.Resource
import com.example.shopapp.data.model.ILocalData

abstract class BaseFragment<VB : ViewBinding, T : ILocalData, VM : BaseViewModel<T>>(layout: Int) :
    Fragment(layout) {

    protected abstract val viewBinding: VB?
    protected abstract val viewModel: VM

    private fun renderData(state: Resource<T>) {
        when (state) {
            is Resource.Error -> renderError(state.error)
            is Resource.Loading -> showLoading()
            is Resource.Success -> renderSuccess(state.data)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.liveData.observe(viewLifecycleOwner) {
            renderData(it)
        }
    }

    protected abstract fun renderSuccess(data: T)

    protected open fun showLoading() {}

    protected open fun renderError(error: Throwable) {
        error.message?.let { showToast(it) }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this.context, msg, Toast.LENGTH_LONG).show()
    }
}