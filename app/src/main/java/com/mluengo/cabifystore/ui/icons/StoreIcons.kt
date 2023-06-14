package com.mluengo.cabifystore.ui.icons

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.mluengo.cabifystore.R

object StoreIcons {
    val ShoppingCart = R.drawable.shopping_cart48px
    val LocalMall = R.drawable.local_mall_48px
    val RemoveIcon = R.drawable.round_remove_24
}

sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}
