package com.example.task2.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.task2.R
import com.example.task2.databinding.MainListAdapterBinding
import com.example.task2.model.responseModel.Category

class CategoriesAdapter(
    var context: Context,
    var mDataList: ArrayList<Category>,
    var mOnCategorySelected: OnCategorySelected
) :
    RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>() {

    inner class MyViewHolder(var mDataBinding: MainListAdapterBinding) :
        RecyclerView.ViewHolder(mDataBinding.root) {

        fun bind(category: Category) {

            mDataBinding.tvTitle.text = category.name

            mDataBinding.cvMain.setOnClickListener {
                mOnCategorySelected.onCategorySelected(adapterPosition)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<MainListAdapterBinding>(
            inflater,
            R.layout.main_list_adapter,
            parent,
            false
        )
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val category = mDataList[position]

        holder.bind(category)




    }


    override fun getItemCount(): Int {
        return mDataList.size
    }

    interface OnCategorySelected {
        fun onCategorySelected(position: Int)

    }


}