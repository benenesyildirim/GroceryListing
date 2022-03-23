package com.listing.grocery.domain.use_case.local

import com.listing.grocery.domain.repository.ProductRepository
import javax.inject.Inject

class GetDbProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend fun getProducts() = repository.getProductsDB()
}