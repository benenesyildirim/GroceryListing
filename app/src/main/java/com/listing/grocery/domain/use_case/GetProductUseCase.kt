package com.listing.grocery.domain.use_case

import com.listing.grocery.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend fun getProduct(id: Int) = repository.getProductDetail(id)
}