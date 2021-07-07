package com.example.task2.api

import com.example.task2.model.responseModel.Category
import com.example.task2.model.responseModel.Ranking

class Constants {
    companion object {
        const val TITLE = "TITLE"
        const val CATEGORY_LIST = "CL"
        const val RANKING_LIST = "RL"
        var categoryList = ArrayList<Category>()
        var rankingList = ArrayList<Ranking>()
    }
}