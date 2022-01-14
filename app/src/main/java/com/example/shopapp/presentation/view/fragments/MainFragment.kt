package com.example.shopapp.presentation.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.annotation.ArrayRes
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.shopapp.data.imageloader.ImageLoader
import com.example.shopapp.data.model.local.BestSeller
import com.example.shopapp.data.model.local.HomeStore
import com.example.shopapp.presentation.adapter.listeners.BaseItemListener
import com.example.shopapp.presentation.adapter.listeners.BestSellerItemListener
import com.example.shopapp.presentation.viewmodel.MainScreenViewModel
import com.example.shopapp.R
import com.example.shopapp.core.adapter.BaseAdapter
import com.example.shopapp.core.view.BaseFragment
import com.example.shopapp.core.adapter.HorizontalItemDecoration
import com.example.shopapp.data.model.local.Main
import com.example.shopapp.databinding.FragmentMainBinding
import com.example.shopapp.presentation.adapter.*
import com.example.shopapp.utils.constants.PHONES_CATEGORY_ID
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class MainFragment : BaseFragment<FragmentMainBinding,
        Main,
        MainScreenViewModel>(R.layout.fragment_main), ItemStateListener {


    override val viewBinding: FragmentMainBinding by viewBinding()
    override val viewModel: MainScreenViewModel by viewModel()

    private var adapterHotSales: BaseAdapter<HomeStore, BaseItemListener>? = null
    private var adapterBestSeller: BaseAdapter<BestSeller, BestSellerItemListener>? = null
    private val imageLoader: ImageLoader by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun renderSuccess(data: Main) {
        data.homeStore?.let { adapterHotSales?.setData(it) }
        data.bestSeller?.let { adapterBestSeller?.setData(it) }
        showProducts()
    }

    override fun getActiveItem(categoryId: Int) {
        when (categoryId) {
            PHONES_CATEGORY_ID -> {
                viewModel.getMain()
            }
            else -> {
                initEmptyLists()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJob()
    }

    private fun init() {
        initRVCategory()
        initRVBestSeller()
        initRVHotSales()
        initBottomSheet()
        initSpinner(viewBinding.bottomSheet.spinnerBrand, R.array.brand_phone_array)
        initSpinner(viewBinding.bottomSheet.spinnerPrice, R.array.price_phone_array)
        initSpinner(viewBinding.bottomSheet.spinnerSize, R.array.size_phone_array)

    }

    private fun initSpinner(spinner: Spinner, @ArrayRes textArrayResId: Int) {
        ArrayAdapter.createFromResource(
            this.requireContext(),
            textArrayResId,
            R.layout.item_spinner
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    private fun initBottomSheet() {
        val bottomSheetDialogFragment =
            BottomSheetBehavior.from(viewBinding.bottomSheet.bottomSheetMainScreen)

        createListenerFilter(bottomSheetDialogFragment)

        with(viewBinding.bottomSheet) {
            ivCloseContainer.setOnClickListener {
                bottomSheetDialogFragment.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
    }

    private fun createListenerFilter(bottomSheetDialogFragment: BottomSheetBehavior<CardView>) {
        viewBinding.ivFilter.setOnClickListener {
            if (bottomSheetDialogFragment.state != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetDialogFragment.state = BottomSheetBehavior.STATE_EXPANDED
            } else {
                bottomSheetDialogFragment.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
    }

    private fun initRVHotSales() {
        adapterHotSales = BaseAdapter(
            R.layout.item_hot_sales,
            listener
        ) { view, data, listener ->
            bindHotSales(view, data, listener, imageLoader)
        }

        with(viewBinding.rvHotSales) {
            adapterHotSales?.let {
                adapter = it
            }
            addItemDecoration(
                HorizontalItemDecoration(
                    DIVIDER_HOT_SALES,
                    EDGE_MARGIN_HOT_SALES
                )
            )
        }
    }

    private fun initRVBestSeller() {
        viewBinding.rvBestSeller.layoutManager =
            GridLayoutManager(this.context, SPAN_COUNT_BEST_SELLER)
        adapterBestSeller = BaseAdapter(
            R.layout.item_best_seller,
            listenerBestSeller
        ) { view, data, listener ->
            bindBestSeller(view, data, listener, imageLoader)
        }
        adapterBestSeller?.let {
            viewBinding.rvBestSeller.adapter = it
        }
    }

    private fun initEmptyLists() {
        with(viewBinding) {
            adapterHotSales?.setData(listOf())
            adapterBestSeller?.setData(listOf())
            hideProducts()
        }
    }

    private fun initRVCategory() {
        with(viewBinding.rvCategory) {
            adapter = CategoriesRVAdapter(this@MainFragment)
            addItemDecoration(
                HorizontalItemDecoration(
                    DIVIDER_CATEGORY,
                    EDGE_MARGIN_CATEGORY
                )
            )
        }
    }

    private fun hideProducts() {
        with(viewBinding) {
            listsProducts.visibility = View.INVISIBLE
            tvEmpty.visibility = View.VISIBLE
        }
    }

    private fun showProducts() {
        with(viewBinding) {
            listsProducts.visibility = View.VISIBLE
            tvEmpty.visibility = View.GONE
            loadingIndicator.visibility = View.GONE
        }
    }

    override fun showLoading() {
        with(viewBinding) {
            tvEmpty.visibility = View.GONE
            listsProducts.visibility = View.INVISIBLE
            loadingIndicator.visibility = View.VISIBLE
        }
    }

    private val listener = object : BaseItemListener {}

    private val listenerBestSeller = object : BestSellerItemListener {
        override fun itemListener(id: String) {
            viewModel.toProductDetailsScreen(id)
        }
    }

    companion object {
        private const val DIVIDER_CATEGORY = 23
        private const val EDGE_MARGIN_CATEGORY = 27
        private const val DIVIDER_HOT_SALES = 30
        private const val EDGE_MARGIN_HOT_SALES = 25
        private const val SPAN_COUNT_BEST_SELLER = 2
    }
}