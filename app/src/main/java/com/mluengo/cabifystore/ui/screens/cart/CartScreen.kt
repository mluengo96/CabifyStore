package com.mluengo.cabifystore.ui.screens.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mluengo.cabifystore.R
import com.mluengo.cabifystore.data.model.ProductModel
import com.mluengo.cabifystore.ui.components.ItemCard
import com.mluengo.cabifystore.ui.theme.CabifyPurple

@Composable
internal fun CartRoute(
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val cartList = cartViewModel.getCart().collectAsState(initial = listOf()).value
    cartViewModel.calculateDiscounts(cartList)

    if (cartList.isNotEmpty()) {
        CartFull(
            cartList = cartList,
            cartViewModel = cartViewModel,
        )
    } else {
        CartEmpty()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartFull(
    cartList: List<ProductModel>,
    cartViewModel: CartViewModel,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        LazyColumn(
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
                .padding(horizontal = 16.dp)
        ) {
            items(cartList) { item ->
                ItemCard(
                    cartItem = item,
                    cartViewModel = cartViewModel,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .padding(top = 64.dp)
                .fillMaxWidth(),
        ) {
            Text(
                text = stringResource(id = R.string.total),
                color = Color.Black,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
            )

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End,
                modifier = Modifier,
            ) {

                val totalPrice = cartViewModel.calculateTotalPrice(cartList)
                val totalDiscPrice = cartViewModel.calculateTotalDiscountedPrice(cartList)

                Text(
                    text = "$totalDiscPrice€",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                )

                if (totalPrice != totalDiscPrice) {
                    Text(
                        text = "$totalPrice€",
                        color = Color.Gray,
                        style = MaterialTheme.typography.titleMedium.copy(textDecoration = TextDecoration.LineThrough),
                        modifier = Modifier
                    )
                }
            }
        }

        val openDialog = remember { mutableStateOf(false) }

        ElevatedButton(
            onClick = { openDialog.value = true },
            colors = ButtonDefaults.elevatedButtonColors(CabifyPurple),
            shape = ShapeDefaults.Small,
            elevation = ButtonDefaults.buttonElevation(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(stringResource(id = R.string.checkout_button), color = Color.White)
        }

        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                }
            ) {
                Surface(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    shape = MaterialTheme.shapes.large,
                    tonalElevation = AlertDialogDefaults.TonalElevation
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = stringResource(id = R.string.purchase_dialog_text),
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        TextButton(
                            onClick = {
                                openDialog.value = false
                            },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text(stringResource(id = R.string.purchase_dialog_dismiss))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CartEmpty() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(id = R.string.empty_cart_text),
            color = Color.Black,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
    CartRoute()
}