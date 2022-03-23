package com.listing.grocery.domain.use_case.local

import com.listing.grocery.data.local.entity.ProductEntity
import com.listing.grocery.domain.repository.ProductRepository
import javax.inject.Inject

class InsertProductsDbUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend fun insertProduct(products: List<ProductEntity>) = repository.insertProducts(products)
}