package com.listing.grocery.presentation.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.listing.grocery.common.Resource
import com.listing.grocery.data.remote.dto.ProductDto
import com.listing.grocery.domain.use_case.local.GetDbProductsUseCase
import com.listing.grocery.domain.use_case.local.InsertProductsDbUseCase
import com.listing.grocery.domain.use_case.remote.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getDbProductsUseCase: GetDbProductsUseCase,
    private val insertProductsDbUseCase: InsertProductsDbUseCase,
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    init {
        getProducts()
    }

    private val _productsLiveData = MutableLiveData<Resource<List<ProductDto>>>()
    val productsLiveData: LiveData<Resource<List<ProductDto>>> get() = _productsLiveData

    private fun getProducts() = viewModelScope.launch {
        getDbProductsUseCase.getProducts().map { it.toProduct() }.let { dbProductList ->
            _productsLiveData.postValue(Resource.Loading(dbProductList))

            try {
                getProductsUseCase.getProducts().let { it ->
                    val products = it.body()!!.products
                    insertProductsDbUseCase.insertProduct(products.map { it.toProductDto() })
                }
            } catch (e: HttpException) {//Invalid Response
                _productsLiveData.postValue(
                    Resource.Error(
                        e.localizedMessage ?: "An unexpected error occurred",
                        dbProductList
                    )
                )
            } catch (e: IOException) {//Server,connection problems
                _productsLiveData.postValue(
                    Resource.Error(
                        "Couldn't reach server. Check your internet connection.",
                        dbProductList
                    )
                )
            }
        }

        getDbProductsUseCase.getProducts().map { it.toProduct() }.let { dbProductList ->
            if (!dbProductList.isNullOrEmpty())
                _productsLiveData.postValue(Resource.Success(dbProductList))
        }
    }
}