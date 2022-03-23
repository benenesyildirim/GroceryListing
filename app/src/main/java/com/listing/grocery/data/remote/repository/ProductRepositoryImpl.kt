package com.listing.grocery.data.remote.repository

import com.listing.grocery.data.ProductApi
import com.listing.grocery.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl  @Inject constructor(
    private val api: ProductApi
) : ProductRepository {
    override suspend fun getProducts() = api.getProducts()

    override suspend fun getProductDetail(id: Int) = api.getProductDetail(id)
}