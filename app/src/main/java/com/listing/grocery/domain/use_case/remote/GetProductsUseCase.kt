package com.listing.grocery.domain.use_case.remote

import com.listing.grocery.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend fun getProducts() = repository.getProducts()
}