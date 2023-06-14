package com.mluengo.cabifystore.domain.repository

import com.mluengo.cabifystore.data.model.DiscountedModel

interface DiscountsRepository {
    fun twoPerOneDisc(): String
    fun quantityDisc(): DiscountedModel
}