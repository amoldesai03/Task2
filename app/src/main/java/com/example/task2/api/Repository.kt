package com.example.task2.api

import com.example.task2.model.responseModel.MainResponseModel
import retrofit2.Call
import retrofit2.Response

class Repository {

    fun getProductList(onResult: (isSuccess: Boolean, response: MainResponseModel?) -> Unit) {

        ApiInterface.create().getProducts().enqueue(object : retrofit2.Callback<MainResponseModel> {
            override fun onResponse(
                call: Call<MainResponseModel>,
                response: Response<MainResponseModel>
            ) {
                if (response.isSuccessful)
                    onResult(true, response.body()!!)
                else
                    onResult(false, null)
            }

            override fun onFailure(call: Call<MainResponseModel>, t: Throwable) {
                onResult(false, null)
            }

        })

    }

    companion object {
        private var INSTANCE: Repository? = null
        fun getInstance() = INSTANCE
            ?: Repository().also {
                INSTANCE = it
            }
    }
}