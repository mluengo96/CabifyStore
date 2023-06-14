package com.mluengo.cabifystore.data.repository

import com.mluengo.cabifystore.data.model.DiscountedModel
import com.mluengo.cabifystore.data.source.DiscountsDataSource
import com.mluengo.cabifystore.domain.repository.DiscountsRepository
import javax.inject.Inject

class DiscountsRepositoryImpl @Inject constructor(
    private val discountsDataSource: DiscountsDataSource
): DiscountsRepository {
    override fun twoPerOneDisc(): String =
        discountsDataSource.twoPerOneDisc()

    override fun quantityDisc(): DiscountedModel =
        discountsDataSource.quantityDisc()
}