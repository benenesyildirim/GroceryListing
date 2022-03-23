package com.listing.grocery.domain.use_case.local

import com.listing.grocery.domain.repository.ProductRepository
import javax.inject.Inject

class GetDbProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend fun getProduct(id: String) = repository.getProductDB(id)
}