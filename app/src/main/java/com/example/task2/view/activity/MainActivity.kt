package com.example.task2.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task2.R
import com.example.task2.api.Constants
import com.example.task2.databinding.ActivityMainBinding
import com.example.task2.model.responseModel.Category
import com.example.task2.model.responseModel.Ranking
import com.example.task2.utils.toggleSwitch
import com.example.task2.view.adapter.CategoriesAdapter
import com.example.task2.view.adapter.RankingsAdapter
import com.example.task2.viewmodel.ListViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener,
    CategoriesAdapter.OnCategorySelected, RankingsAdapter.OnRankingSelected {
    private lateinit var binding: ActivityMainBinding
    private var toggleFlag = false
    private lateinit var categoriesAdapter: CategoriesAdapter
    var categoryList = ArrayList<Category>()
    var rankingList = ArrayList<Ranking>()
    private lateinit var rankingAdapter: RankingsAdapter
    private lateinit var mViewModel: ListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        addObserver()
        initUI()


    }

    private fun addObserver() {
        mViewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        mViewModel.productData.observe(this, { responseModel ->
            categoryList.clear()
            categoryList.addAll(responseModel.categories)
            categoriesAdapter.notifyDataSetChanged()
            rankingList.clear()
            rankingList.addAll(responseModel.rankings)
            rankingAdapter.notifyDataSetChanged()
            Constants.categoryList.clear()
            Constants.categoryList.addAll(responseModel.categories)
            Constants.rankingList.clear()
            Constants.rankingList.addAll(responseModel.rankings)

        })

        mViewModel.dataLoading.observe(this, { loading ->
            if (loading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }

        })
    }

    private fun initUI() {

        binding.clCategories.setOnClickListener(this)
        binding.clRankings.setOnClickListener(this)
        categoriesAdapter = CategoriesAdapter(this, categoryList, this)
        binding.rvCategories.adapter = categoriesAdapter
        binding.rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rankingAdapter = RankingsAdapter(this, rankingList, this)
        binding.rvRankings.adapter = rankingAdapter
        binding.rvRankings.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        mViewModel.fetchProductData()


    }

    override fun onClick(v: View?) {
        when (v) {
            binding.clCategories, binding.clRankings -> {
                toggleSwitch(
                    binding.clRankings,
                    binding.tvRankings,
                    binding.tvCategories,
                    binding.clCategories,
                    toggleFlag
                )
                if (toggleFlag) {
                    binding.rvCategories.visibility = View.VISIBLE
                    binding.rvRankings.visibility = View.GONE
                } else {
                    binding.rvCategories.visibility = View.GONE
                    binding.rvRankings.visibility = View.VISIBLE
                }
                toggleFlag = !toggleFlag
            }
        }
    }

    override fun onCategorySelected(position: Int) {
        val intent = Intent(this, ProductListActivity::class.java)
        intent.putExtra(Constants.TITLE, categoryList[position].name)
        intent.putExtra(Constants.CATEGORY_LIST, position)
        startActivity(intent)

    }

    override fun onRankingSelected(position: Int) {
        val intent = Intent(this, ProductListActivity::class.java)
        intent.putExtra(Constants.TITLE, rankingList[position].ranking)
        intent.putExtra(Constants.RANKING_LIST, position)
        startActivity(intent)

    }
}