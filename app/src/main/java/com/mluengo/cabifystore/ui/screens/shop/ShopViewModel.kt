package com.mluengo.cabifystore.ui.screens.shop

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mluengo.cabifystore.data.model.ProductModel
import com.mluengo.cabifystore.domain.repository.DiscountsRepository
import com.mluengo.cabifystore.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

sealed interface ShopUiState {
    data class Success(val products: List<ProductModel>) : ShopUiState
    object Error : ShopUiState
    object Loading : ShopUiState
}

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val discountsRepository: DiscountsRepository,
    @ApplicationContext val context: Context,
): ViewModel() {
    var shopState: ShopUiState by mutableStateOf(ShopUiState.Loading)
        private set

    private val _selectedProduct = MutableStateFlow(ProductModel("", "", 0.0))
    var selectedProduct = _selectedProduct.asStateFlow()

    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            shopState = ShopUiState.Loading
            shopState = try {
                ShopUiState.Success(productRepository.getProducts().flattenToList())
            } catch (e: IOException) {
                ShopUiState.Error
            } catch (e: HttpException) {
                ShopUiState.Error
            }
        }
    }

    fun get2Per1Discounts(): String =
        discountsRepository.twoPerOneDisc()

    fun updateSelectedProduct(selectedProductModel: ProductModel) {
        _selectedProduct.value = selectedProductModel
    }

    suspend fun addToCart(product: ProductModel) {
        productRepository.insertProduct(product = product)
    }

    suspend fun <T> Flow<List<T>>.flattenToList() =
        flatMapConcat { it.asFlow() }.toList()
}