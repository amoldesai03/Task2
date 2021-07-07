package com.example.task2.model.responseModel

import java.io.Serializable

data class Product (
    val date_added: String,
    val id: Int,
    val name: String,
    val tax: Tax,
    val variants: List<Variant>
) : Serializable