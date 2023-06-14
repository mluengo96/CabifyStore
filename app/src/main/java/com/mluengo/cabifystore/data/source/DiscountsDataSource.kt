package com.mluengo.cabifystore.data.source

import com.mluengo.cabifystore.data.model.DiscountedModel
import javax.inject.Inject

class DiscountsDataSource @Inject constructor(

) {
    fun twoPerOneDisc(): String =
        "VOUCHER"

    fun quantityDisc(): DiscountedModel =
        DiscountedModel(
            code = "TSHIRT",
            quantity = 3,
            newPrice = 19.00,
        )
}