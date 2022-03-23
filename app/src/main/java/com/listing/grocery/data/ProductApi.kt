package com.listing.grocery.data

import com.listing.grocery.data.remote.dto.ProductDetailDto
import com.listing.grocery.data.remote.dto.ProductDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductApi {
    @GET("/developer-application-test/cart/list")
    suspend fun getProducts(): Response<List<ProductDto>>

    @POST("/developer-application-test/cart/{product_id}/detail")
    suspend fun getProductDetail(@Path("product_id") id: Int): Response<ProductDetailDto>
}