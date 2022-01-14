package com.example.shopapp.core.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalItemDecoration(
    private val divider: Int,
    private val edgeMargin: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val adapter = parent.adapter ?: return
        val position = parent.getChildAdapterPosition(view)

        val isPrevTargetView = adapter.isPrevTargetView(position)
        val isNextTargetView = adapter.isNextTargetView(position)

        with(outRect) {
            left = if (isPrevTargetView) divider else edgeMargin
            right = if (isNextTargetView) divider else edgeMargin
        }
    }

    private fun RecyclerView.Adapter<*>.isPrevTargetView(
        currentPosition: Int
    ): Boolean = currentPosition != 0

    private fun RecyclerView.Adapter<*>.isNextTargetView(
        currentPosition: Int
    ): Boolean = currentPosition != itemCount - 1
}