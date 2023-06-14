package com.mluengo.cabifystore.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mluengo.cabifystore.R
import com.mluengo.cabifystore.ui.theme.CabifyPurple

@Composable
fun StoreAddButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onButtonClicked: () -> Unit,
) {
    ElevatedButton(
        onClick = onButtonClicked,
        enabled = enabled,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(containerColor = CabifyPurple),
        contentPadding = ButtonDefaults.ButtonWithIconContentPadding
    ) {
        Icon(
            Icons.Rounded.Add,
            contentDescription = null,
            modifier = modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(modifier.size(ButtonDefaults.IconSpacing))
        Text(
            text = stringResource(id = R.string.add_button),
            color = Color.White,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StoreAddButtonSelectedPreview() {
    StoreAddButton(
        enabled = false,
        onButtonClicked = { },
    )
}

@Preview(showBackground = true)
@Composable
fun StoreAddButtonUnselectedPreview() {
    StoreAddButton(
        enabled = true,
        onButtonClicked = { },
    )
}