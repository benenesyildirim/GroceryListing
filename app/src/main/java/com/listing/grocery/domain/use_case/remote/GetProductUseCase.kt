package com.listing.grocery.domain.use_case.remote

import com.listing.grocery.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend fun getProduct(id: String) = repository.getProductDetail(id)
}