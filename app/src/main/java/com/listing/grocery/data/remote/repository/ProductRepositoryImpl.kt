package com.listing.grocery.data.remote.repository

import com.listing.grocery.data.ProductApi
import com.listing.grocery.data.local.ProductDao
import com.listing.grocery.data.local.entity.ProductEntity
import com.listing.grocery.data.remote.dto.ProductResponseDto
import com.listing.grocery.domain.repository.ProductRepository
import retrofit2.Response
import javax.inject.Inject

class ProductRepositoryImpl  @Inject constructor(
    private val api: ProductApi,
    private val dao: ProductDao
) : ProductRepository {
    //Remote
    override suspend fun getProducts(): Response<ProductResponseDto> = api.getProducts()

    override suspend fun getProductDetail(id: String) = api.getProductDetail(id)

    //Local
    override suspend fun insertProducts(products: List<ProductEntity>) = dao.insertProducts(products)

    override suspend fun insertProductDetail(product: ProductEntity) = dao.insertProductDetail(product)

    override suspend fun getProductDB(id: String) = dao.getProduct(id)

    override suspend fun getProductsDB() = dao.getProducts()
}