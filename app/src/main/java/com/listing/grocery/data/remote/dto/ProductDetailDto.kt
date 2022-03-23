package com.listing.grocery.data.remote.dto

import com.listing.grocery.data.local.entity.ProductEntity

data class ProductDetailDto(
    val product_id: String,
    val name: String,
    val price: Int,
    val image: String,
    val description: String? = ""
){
    fun toProductEntity() = ProductEntity(
        product_id,
        name,
        price,
        image,
        description ?: ""
    )
}
