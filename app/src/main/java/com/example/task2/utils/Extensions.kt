package com.example.task2.utils

import android.content.Context
import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.TextView
import com.example.task2.R

fun Context.toggleSwitch(
    clRankings: ViewGroup,
    tvRankings: TextView,
    tvCategories: TextView,
    clCategories: ViewGroup,
    toggle: Boolean
) {
    if (toggle) {
        clRankings.apply {
            this.background = null

        }
        clCategories.apply {
            this.background =
                (resources.getDrawable(R.drawable.bg_selected))
        }
        tvRankings.apply {
            this.setTextColor(resources.getColor(R.color.colorFaintGrey))
            typeface = Typeface.DEFAULT


        }
        tvCategories.apply {
            this.setTextColor(resources.getColor(R.color.white))
            typeface = Typeface.DEFAULT_BOLD


        }

    } else {

        clRankings.apply {

            this.background =
                (resources.getDrawable(R.drawable.bg_selected))
        }
        clCategories.apply {
            this.background = null

        }
        tvRankings.apply {
            this.setTextColor(resources.getColor(R.color.white))
            typeface = Typeface.DEFAULT_BOLD

        }
        tvCategories.apply {
            this.setTextColor(resources.getColor(R.color.colorFaintGrey))
            typeface = Typeface.DEFAULT

        }


    }
}
