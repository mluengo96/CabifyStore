package com.mluengo.cabifystore.ui.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.mluengo.cabifystore.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreTopAppBar(

) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(id = R.string.app_bar),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    )
}