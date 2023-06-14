package com.mluengo.cabifystore.ui.screens.cart

import android.content.Context
import androidx.lifecycle.ViewModel
import com.mluengo.cabifystore.data.model.ProductModel
import com.mluengo.cabifystore.domain.repository.DiscountsRepository
import com.mluengo.cabifystore.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val discountsRepository: DiscountsRepository,
    @ApplicationContext val context: Context,
): ViewModel() {

    val twoPOne = discountsRepository.twoPerOneDisc()
    private val discountQt = discountsRepository.quantityDisc()

    fun getCart(): Flow<List<ProductModel>> =
        productRepository.getCart()

    fun calculateDiscounts(cart: List<ProductModel>) {
        for (item in cart) {
            if (item.quantity >= 2 && twoPOne == item.code) {
                val remaineder = item.quantity % 2

                if (remaineder == 0) {
                    item.discountedPrice = (item.price * item.quantity) * 0.5
                } else {
                    val toDiscount = item.quantity - remaineder
                    item.discountedPrice = ((item.price * toDiscount) * 0.5) + (item.price * remaineder)
                }
            }

            if (discountQt.code == item.code && item.quantity >= discountQt.quantity) {
                item.discountedPrice = discountQt.newPrice
            }
        }
    }

    fun calculateTotalPrice(cartList: List<ProductModel>): Double {
        var totalPrice = 0.0
        for (item in cartList) {
            totalPrice += (item.price * item.quantity)
        }
        return totalPrice
    }

    fun calculateTotalDiscountedPrice(cartList: List<ProductModel>): Double {
        var totalDiscPrice = 0.0
        for (item in cartList) {
            totalDiscPrice += if (item.discountedPrice != -1.0) {
                if (item.code == twoPOne) {
                    item.discountedPrice
                } else (item.discountedPrice * item.quantity)
            } else
                (item.price * item.quantity)
        }
        return totalDiscPrice
    }
}