package com.mluengo.cabifystore.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mluengo.cabifystore.data.model.ProductModel
import com.mluengo.cabifystore.ui.screens.cart.CartViewModel
import com.mluengo.cabifystore.ui.theme.CabifyPurple
import com.mluengo.cabifystore.ui.theme.CabifyPurpleLight
import com.mluengo.cabifystore.ui.utils.ProductImageHelper

@Composable
fun ItemCard(
    cartItem: ProductModel,
    cartViewModel: CartViewModel,
    modifier: Modifier = Modifier
) {
    val itemImg: Int = when (cartItem.code) {
        ProductImageHelper.TSHIRT.name -> { ProductImageHelper.TSHIRT.image }
        ProductImageHelper.VOUCHER.name -> { ProductImageHelper.VOUCHER.image }
        ProductImageHelper.MUG.name -> { ProductImageHelper.MUG.image }
        else -> { ProductImageHelper.PLACEHOLDER.image }
    }

    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier.padding(vertical = 4.dp),
        border = BorderStroke(1.dp, CabifyPurple),
        color = CabifyPurpleLight
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = itemImg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
            Column() {
                Text(
                    text = cartItem.name,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    if (cartItem.discountedPrice != -1.0) {
                        val twoPOneCode = cartViewModel.twoPOne
                        if (twoPOneCode == cartItem.code) {
                            Text(
                                text = "${cartItem.discountedPrice}€",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                            Text(
                                text = "${cartItem.price * cartItem.quantity}€",
                                color = Color.Gray,
                                style = MaterialTheme.typography.bodySmall.copy(textDecoration = TextDecoration.LineThrough),
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        } else {
                            Text(
                                text = "${cartItem.discountedPrice * cartItem.quantity}€",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                            Text(
                                text = "${cartItem.price * cartItem.quantity}€",
                                color = Color.Gray,
                                style = MaterialTheme.typography.bodySmall.copy(textDecoration = TextDecoration.LineThrough),
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                    } else {
                        Text(
                            text = "${cartItem.price * cartItem.quantity}€",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
            }
            Column(
                horizontalAlignment = Alignment.End,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Qt: ${cartItem.quantity}",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartItemPreview() {
    ItemCard(
        cartItem = ProductModel(
            name = "Cabify Voucher",
            code = "VOUCHER",
            price = 5.0,
            quantity = 2,
        ),
        cartViewModel = viewModel(),
        modifier = Modifier.fillMaxWidth()
    )
}