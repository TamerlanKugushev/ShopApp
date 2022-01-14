package com.example.shopapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.presentation.view.fragments.ItemStateListener
import com.example.shopapp.R
import com.example.shopapp.data.model.Category
import com.example.shopapp.databinding.ItemCategoriesMainScreenBinding
import com.example.shopapp.utils.constants.BOOKS_CATEGORY_ID
import com.example.shopapp.utils.constants.COMPUTER_CATEGORY_ID
import com.example.shopapp.utils.constants.HEALTH_CATEGORY_ID
import com.example.shopapp.utils.constants.PHONES_CATEGORY_ID

class CategoriesRVAdapter(private val itemStateListener: ItemStateListener) :
    RecyclerView.Adapter<CategoriesRVAdapter.ViewHolder>() {

    private val categories = listOf(
        Category(
            PHONES_CATEGORY_ID,
            R.drawable.category_phones_image,
            R.string.category_phones,
            true
        ),
        Category(
            COMPUTER_CATEGORY_ID,
            R.drawable.category_computer_image,
            R.string.category_computers
        ),
        Category(
            HEALTH_CATEGORY_ID,
            R.drawable.category_health_image,
            R.string.category_heals
        ),
        Category(
            BOOKS_CATEGORY_ID,
            R.drawable.category_books_image,
            R.string.category_books
        ),
        Category(
            PHONES_CATEGORY_ID,
            R.drawable.category_phones_image,
            R.string.category_phones
        ),
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemCategoriesMainScreenBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int = categories.size

    inner class ViewHolder(private val binding: ItemCategoriesMainScreenBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val context = itemView.context
        private val resources = context.resources
        private val theme = context.theme
        @SuppressLint("NewApi")
        fun bind(category: Category) {
            initView(category)
            chekStateView(category)
            setStateSelected(category)
            onClickItem(category)
        }

        private fun onClickItem(category: Category) {
            itemView.setOnClickListener {
                if (category.isSelected) {
                    return@setOnClickListener
                } else {
                    category.isSelected = true
                    notifyDataSetChanged()
                }
            }
        }

        private fun setStateSelected(category: Category) {
            if (category.isSelected) {
                category.isSelected = !category.isSelected
            }
        }

        @SuppressLint("NewApi")
        private fun chekStateView(category: Category) {
            with(binding) {
                if (category.isSelected) {
                    ivCircleCategories.setColorFilter(
                        resources.getColor(
                            R.color.orange, theme
                        )
                    )
                    descriptionItemCategory.setTextColor(
                        resources.getColor(R.color.orange, theme)
                    )

                    setImageIcon(category.id)
                    itemStateListener.getActiveItem(category.id)
                } else {
                    ivCircleCategories.setColorFilter(
                        resources.getColor(
                            R.color.white, theme
                        )
                    )
                    descriptionItemCategory.setTextColor(
                        resources.getColor(R.color.blue, theme)
                    )
                }
            }
        }

        private fun initView(category: Category) {
            with(binding) {
                ivIconCategories.setImageResource(category.imageRes)
                descriptionItemCategory.text = context.resources.getString(category.description)
            }
        }

        private fun setImageIcon(id: Int) {
            when (id) {
                PHONES_CATEGORY_ID -> {
                    binding.ivIconCategories.setImageResource(R.drawable.category_phones_image_selected)
                }
                COMPUTER_CATEGORY_ID -> {
                    binding.ivIconCategories.setImageResource(R.drawable.category_computer_image_selected)
                }
                HEALTH_CATEGORY_ID -> {
                    binding.ivIconCategories.setImageResource(R.drawable.category_health_image_selected)
                }
                BOOKS_CATEGORY_ID -> {
                    binding.ivIconCategories.setImageResource(R.drawable.category_books_image_selected)
                }
            }
        }
    }
}