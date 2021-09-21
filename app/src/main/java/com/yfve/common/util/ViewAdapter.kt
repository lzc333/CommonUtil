package com.yfve.common.util

import android.view.View
import android.widget.CompoundButton
import android.widget.ImageView
import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.BindingAdapter
import com.yfve.common.util.heightVisibleAnimator


object ViewAdapter {


    /**
     * view的显示隐藏
     */
    @JvmStatic
    @BindingAdapter(value = ["yfve:isVisible"], requireAll = false)
    fun isVisible(view: View, visibility: Boolean) {
        if (visibility) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }

    /**
     * view的显示隐藏动画,仅用于Linearlayout
     */
    @JvmStatic
    @BindingAdapter(value = ["yfve:isVisibleAnimator"], requireAll = false)
    fun isVisibleAnimator(view: View, visibility: Boolean) {
        view.heightVisibleAnimator(visibility)
    }

    @JvmStatic
    @BindingAdapter(value = ["yfve:switchCompatIsChecked"], requireAll = false)
    fun switchCompatIsChecked(view: SwitchCompat, listener: OnSwitchCompatChecked) {
         view.setOnClickListener{
             listener.onSwitchCompatChecked(view.isChecked)
         }
      }



}

interface OnSwitchCompatChecked {
    fun onSwitchCompatChecked(isChecked: Boolean)
}
