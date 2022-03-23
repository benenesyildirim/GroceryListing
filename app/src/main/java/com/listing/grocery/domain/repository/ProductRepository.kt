package com.listing.grocery.domain.repository

import com.listing.grocery.data.remote.dto.ProductDetailDto
import com.listing.grocery.data.remote.dto.ProductDto
import retrofit2.Response
import retrofit2.http.Path

interface ProductRepository {
    suspend fun getProducts(): Response<List<ProductDto>>

    suspend fun getProductDetail(@Path("product_id") id: Int): Response<ProductDetailDto>
}