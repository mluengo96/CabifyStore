package com.mluengo.cabifystore.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mluengo.cabifystore.R
import com.mluengo.cabifystore.data.model.ProductModel
import com.mluengo.cabifystore.ui.screens.shop.ShopViewModel
import com.mluengo.cabifystore.ui.theme.CabifyPurple
import com.mluengo.cabifystore.ui.theme.CabifyPurpleLight
import com.mluengo.cabifystore.ui.utils.ProductImageHelper.MUG
import com.mluengo.cabifystore.ui.utils.ProductImageHelper.PLACEHOLDER
import com.mluengo.cabifystore.ui.utils.ProductImageHelper.TSHIRT
import com.mluengo.cabifystore.ui.utils.ProductImageHelper.VOUCHER

@Composable
fun ShopItem(
    product: ProductModel,
    modifier: Modifier = Modifier,
    checked: Boolean,
    twoPerOneCode: String,
    onItemClicked: (ProductModel) -> Unit
) {
    val productImg: Int = when (product.code) {
        TSHIRT.name -> { TSHIRT.image }
        VOUCHER.name -> { VOUCHER.image }
        MUG.name -> { MUG.image }
        else -> { PLACEHOLDER.image }
    }

    Surface(
        modifier = modifier,
        color = if (checked) CabifyPurpleLight else Color.White,
        onClick = { onItemClicked(product) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 4.dp, vertical = 8.dp)
                .fillMaxWidth()
        ) {

            VerticalDivider(checked)

            Image(
                painter = painterResource(id = productImg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .requiredSize(64.dp)
                    .padding(start = if (checked) 8.dp else 11.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
            Column {
                Text(
                    text = product.name,
                    color = if (checked) colorResource(id = R.color.black) else colorResource(id = R.color.grey),
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    if (product.code == twoPerOneCode) {
                        Text(
                            text = stringResource(id = R.string.two_per_one_discount),
                            color = if (checked) colorResource(id = R.color.purple_500) else colorResource(id = R.color.grey),
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "${product.price}€",
                    color = if (checked) colorResource(id = R.color.black) else colorResource(id = R.color.grey),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Composable
fun VerticalDivider(
    checked: Boolean,
    modifier: Modifier = Modifier,
    color: Color = CabifyPurple.copy(alpha = DividerAlpha),
    thickness: Dp = 3.dp
) {
    AnimatedVisibility(
        visible = checked,
        enter = fadeIn(initialAlpha = .4f),
        exit = fadeOut(animationSpec = tween(durationMillis = 250))
    ) {
        Box(
            modifier
                .height(64.dp)
                .width(thickness)
                .clip(CircleShape)
                .background(color = color)
        ) {

        }
    }
}

private const val DividerAlpha = 1f

@Preview("Selected Item", showBackground = true)
@Composable
fun ShopItemSelectedPreview() {
    val product = ProductModel(
        "VOUCHER",
        "Cabify Voucher",
        5.0
    )
    ShopItem(product, checked = true, twoPerOneCode = "VOUCHER") {}
}

@Preview("Unselected Item", showBackground = true)
@Composable
fun ShopItemUnselectedPreview() {
    val product = ProductModel(
        "TSHIRT",
        "Cabify T-Shirt",
        20.0
    )
    ShopItem(product, checked = false, twoPerOneCode = "MUG") {}
}