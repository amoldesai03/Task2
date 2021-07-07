package com.example.task2.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task2.R
import com.example.task2.api.Constants
import com.example.task2.databinding.ActivityProductListBinding
import com.example.task2.model.responseModel.Product
import com.example.task2.view.adapter.ProductAdapter

class ProductListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductListBinding
    private lateinit var adapter: ProductAdapter
    private var products = ArrayList<Product>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_list)

        initUI()


    }


    private fun initUI() {

        adapter = ProductAdapter(this, products)
        binding.rvProduct.adapter = adapter
        binding.rvProduct.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        intent.let {
            binding.tvActionBar.text = intent.getStringExtra(Constants.TITLE)
            if (intent.hasExtra(Constants.CATEGORY_LIST)) {
                products.clear()
                products.addAll(
                    Constants.categoryList[it.getIntExtra(
                        Constants.CATEGORY_LIST,
                        0
                    )].products
                )

            } else  {
                products.clear()
                products.addAll(
                    Constants.rankingList[it.getIntExtra(
                        Constants.RANKING_LIST,
                        0
                    )].products
                )
            }
        }

        adapter.notifyDataSetChanged()


    }


}