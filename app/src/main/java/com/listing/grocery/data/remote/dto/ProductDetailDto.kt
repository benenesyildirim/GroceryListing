package com.listing.grocery.data.remote.dto

data class ProductDetailDto(
    val product_id: Int,
    val name: String,
    val price: Int,
    val image: String,
    val description: String
)
