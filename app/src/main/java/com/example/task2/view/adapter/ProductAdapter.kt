package com.example.task2.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.task2.R
import com.example.task2.api.Constants
import com.example.task2.databinding.MainListAdapterBinding
import com.example.task2.databinding.ProductListAdapterBinding
import com.example.task2.model.responseModel.Product

class ProductAdapter(
    var context: Context,
    var mDataList: ArrayList<Product>
    ) :
    RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    inner class MyViewHolder(var mDataBinding: ProductListAdapterBinding) :
        RecyclerView.ViewHolder(mDataBinding.root) {

        fun bind(product: Product) {
            try {
                mDataBinding.tvTitle.text = product.name
                mDataBinding.tvTax.text = "Tax : ${product.tax.value}"
                mDataBinding.tvVarients.text = "Variants :"

                for(variants in product.variants) {
                    mDataBinding.tvVarients.append("${variants.color} ")
                }

                mDataBinding.cvMain.setOnClickListener {
                    Toast.makeText(context , product.name , Toast.LENGTH_SHORT).show()
                }

            } catch (ignored : Exception) {

            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ProductListAdapterBinding>(
            inflater,
            R.layout.product_list_adapter,
            parent,
            false
        )
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val product = mDataList[position]

        val categories = Constants.categoryList

        for(category in categories) {
            for ( products in category.products) {
                if(products.id == product.id) {
                    holder.bind(products)
                }
            }
        }





    }


    override fun getItemCount(): Int {
        return mDataList.size
    }

    interface OnProductSelected {
        fun onProductSelected(position: Int)

    }


}