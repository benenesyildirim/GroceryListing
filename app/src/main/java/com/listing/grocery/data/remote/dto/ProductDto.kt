package com.listing.grocery.data.remote.dto

import com.listing.grocery.data.local.entity.ProductEntity

data class ProductDto(
    val product_id: String,
    val name: String,
    val price: Int,
    val image: String
) {
    fun toProductDto() = ProductEntity(
        product_id,
        name,
        price,
        image
    )
}