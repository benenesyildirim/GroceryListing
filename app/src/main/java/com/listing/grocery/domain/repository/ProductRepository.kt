package com.listing.grocery.domain.repository

import com.listing.grocery.data.local.entity.ProductEntity
import com.listing.grocery.data.remote.dto.ProductDetailDto
import com.listing.grocery.data.remote.dto.ProductResponseDto
import retrofit2.Response

interface ProductRepository {
    //Remote
    suspend fun getProducts(): Response<ProductResponseDto>

    suspend fun getProductDetail(id: String): Response<ProductDetailDto>

    //Local
    suspend fun insertProducts(products: List<ProductEntity>)

    suspend fun insertProductDetail(product: ProductEntity)

    suspend fun getProductDB(id: String): ProductEntity

    suspend fun getProductsDB(): List<ProductEntity>
}