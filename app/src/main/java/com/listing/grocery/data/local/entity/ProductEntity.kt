package com.listing.grocery.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.listing.grocery.data.remote.dto.ProductDetailDto
import com.listing.grocery.data.remote.dto.ProductDto

@Entity
data class ProductEntity(
    @PrimaryKey val product_id: String,
    val name: String,
    val price: Int,
    val image: String,
    val description: String = ""
){
    fun toProduct() = ProductDto(
        product_id,
        name,
        price,
        image
    )

    fun toProductDetail() = ProductDetailDto(
        product_id,
        name,
        price,
        image,
        description
    )
}
