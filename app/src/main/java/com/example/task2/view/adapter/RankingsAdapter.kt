package com.example.task2.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.task2.R
import com.example.task2.databinding.MainListAdapterBinding
import com.example.task2.model.responseModel.Ranking

class RankingsAdapter(
    var context: Context,
    var mDataList: ArrayList<Ranking>,
    var mOnRankingSelected: OnRankingSelected
) :
    RecyclerView.Adapter<RankingsAdapter.MyViewHolder>() {

    inner class MyViewHolder(var mDataBinding: MainListAdapterBinding) :
        RecyclerView.ViewHolder(mDataBinding.root) {

        fun bind(ranking: Ranking) {

            mDataBinding.tvTitle.text = ranking.ranking

            mDataBinding.cvMain.setOnClickListener {
                mOnRankingSelected.onRankingSelected(adapterPosition)
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

        val ranking = mDataList[position]

        holder.bind(ranking)




    }


    override fun getItemCount(): Int {
        return mDataList.size
    }

    interface OnRankingSelected {
        fun onRankingSelected(position: Int)

    }


}