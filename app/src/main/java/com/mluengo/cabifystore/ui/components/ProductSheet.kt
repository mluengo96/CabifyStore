package com.mluengo.cabifystore.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mluengo.cabifystore.R
import com.mluengo.cabifystore.data.model.DiscountedModel
import com.mluengo.cabifystore.data.model.ProductModel
import com.mluengo.cabifystore.ui.screens.shop.ShopViewModel
import com.mluengo.cabifystore.ui.theme.CabifyPurple
import com.mluengo.cabifystore.ui.theme.CabifyPurpleLight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductSheet(
    scaffoldState: BottomSheetScaffoldState,
    coroutineScope: CoroutineScope,
    product: ProductModel,
    shopViewModel: ShopViewModel,
    productImg: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Card(
            elevation = CardDefaults.cardElevation(1.dp)
        ) {
            Image(
                painter = painterResource(id = productImg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(256.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }

        Text(
            text = product.name,
            color = colorResource(id = R.color.black),
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(16.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "${product.price}€",
                color = colorResource(id = R.color.black),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(8.dp)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight()
        ) {
            var currentProductQt by remember { mutableStateOf(1) }

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                FilledTonalIconButton(
                    onClick = { currentProductQt-- },
                    modifier = Modifier,
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.round_remove_24),
                        contentDescription = null,
                        tint = CabifyPurple,
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                }

                Text(
                    text = "$currentProductQt",
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                FilledIconButton(
                    onClick = { currentProductQt++ },
                    modifier = Modifier,
                ) {
                    Icon(
                        Icons.Rounded.Add,
                        contentDescription = null,
                        tint = CabifyPurpleLight,
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                }
            }

            StoreAddButton(
                onButtonClicked = {
                    product.quantity = currentProductQt
                    currentProductQt = 1
                    coroutineScope.launch {
                        shopViewModel.addToCart(product)
                        scaffoldState.bottomSheetState.hide()
                    }
                },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun ProductSheetPreview() {
    BottomSheetScaffold(
        sheetPeekHeight = 800.dp,
        sheetContent = {
            ProductSheet(
                scaffoldState = rememberBottomSheetScaffoldState(),
                coroutineScope = rememberCoroutineScope(),
                ProductModel(
                    "",
                    "",
                    4.4
                ),
                productImg = R.drawable.cabify_tshirt,
                shopViewModel = viewModel()
            )
        }
    ) {}
}