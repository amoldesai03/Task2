package com.example.task2.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.task2.api.Repository
import com.example.task2.model.responseModel.MainResponseModel

class ListViewModel : BaseViewModel() {
    val productData = MutableLiveData<MainResponseModel>()

    fun fetchProductData() {
        dataLoading.value = true
        Repository.getInstance().getProductList { isSuccess, response ->
            dataLoading.value = false
            if(isSuccess) {
                productData.value = response
                empty.value = false
            }
            else {
                empty.value = false
            }
        }

    }
}