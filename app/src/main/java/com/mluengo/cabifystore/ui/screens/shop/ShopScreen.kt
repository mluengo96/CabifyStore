package com.mluengo.cabifystore.ui.screens.shop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mluengo.cabifystore.R
import com.mluengo.cabifystore.data.model.ProductModel
import com.mluengo.cabifystore.ui.components.ProductSheet
import com.mluengo.cabifystore.ui.components.ShopItem
import com.mluengo.cabifystore.ui.utils.ProductImageHelper
import kotlinx.coroutines.launch

@Composable
internal fun ShopRoute(
    shopState: ShopUiState,
    shopViewModel: ShopViewModel,
) {
    ShopBottomSheet(shopState, shopViewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopBottomSheet(
    shopState: ShopUiState,
    shopViewModel: ShopViewModel,
) {
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = rememberStandardBottomSheetState(skipHiddenState = false))
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            val selectedProduct = shopViewModel.selectedProduct.collectAsState().value
            val productImg: Int = when (selectedProduct.code) {
                ProductImageHelper.TSHIRT.name -> { ProductImageHelper.TSHIRT.image }
                ProductImageHelper.VOUCHER.name -> { ProductImageHelper.VOUCHER.image }
                ProductImageHelper.MUG.name -> { ProductImageHelper.MUG.image }
                else -> { ProductImageHelper.PLACEHOLDER.image }
            }

            ProductSheet(
                scaffoldState = scaffoldState,
                coroutineScope = coroutineScope,
                product = selectedProduct,
                productImg = productImg,
                shopViewModel = shopViewModel
            )
        }
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            when(shopState) {
                is ShopUiState.Loading -> Unit
                is ShopUiState.Success -> {
                    ShopScreen(shopState.products, shopViewModel) { product ->
                        shopViewModel.updateSelectedProduct(product)
                        coroutineScope.launch { scaffoldState.bottomSheetState.expand() }
                    }
                }
                is ShopUiState.Error -> Text(
                    text = stringResource(id = R.string.error),
                    color = Color.Black,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun ShopScreen(
    productList: List<ProductModel>,
    shopViewModel: ShopViewModel,
    onItemClick: (ProductModel) -> Unit,
) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(id = R.string.new_products_title),
            color = Color.Black,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(16.dp)
        )

        var selectedIndex by remember {
            mutableStateOf(-1)
        }

        LazyColumn(
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
        ) {
            itemsIndexed(productList) { index, product ->
                if (selectedIndex == index) {
                    shopViewModel.updateSelectedProduct(productList[selectedIndex])
                }

                // Get product with 2 per 1 discount
                val twoPerOneCode = shopViewModel.get2Per1Discounts()
                ShopItem(
                    product,
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = selectedIndex == index,
                            onClick = { selectedIndex = index }
                        ),
                    selectedIndex == index,
                    twoPerOneCode,
                ) { onItemClick(product) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShopScreenPreview() {
    val productList = listOf<ProductModel>()
    ShopBottomSheet(shopState = ShopUiState.Success(productList), viewModel())
}