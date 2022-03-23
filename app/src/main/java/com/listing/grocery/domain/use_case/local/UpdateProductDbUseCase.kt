package com.listing.grocery.domain.use_case.local

import com.listing.grocery.data.local.entity.ProductEntity
import com.listing.grocery.domain.repository.ProductRepository
import javax.inject.Inject

class UpdateProductDbUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend fun updateProduct(product: ProductEntity) = repository.insertProductDetail(product)
}