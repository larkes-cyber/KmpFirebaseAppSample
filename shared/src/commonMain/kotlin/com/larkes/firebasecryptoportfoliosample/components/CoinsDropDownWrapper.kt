package com.larkes.firebasecryptoportfoliosample.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.add.models.CoinUI
import com.larkes.firebasecryptoportfoliosample.theme.Theme
import com.larkes.firebasecryptoportfoliosample.utils.SharedService

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinsDropDownWrapper(
    modifier: Modifier = Modifier,
    selectedCoin:CoinUI,
    coins:List<CoinUI>,
    onSelected:(CoinUI) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }


    ExposedDropdownMenuBox(

        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {

        Row {
            OutlinedTextField(
                value = selectedCoin.title,
                onValueChange = {

                },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = modifier.menuAnchor(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Theme.colors.primary,
                    focusedContainerColor =  Theme.colors.primary,
                    unfocusedTextColor = Theme.colors.title,
                    focusedTextColor = Theme.colors.title,
                    focusedIndicatorColor = Theme.colors.title.copy(alpha = 0.6f),
                    unfocusedIndicatorColor = Theme.colors.title,
                    unfocusedTrailingIconColor = Theme.colors.title,
                    focusedTrailingIconColor = Theme.colors.title
                )
            )

        }

        ExposedDropdownMenu(
            modifier = Modifier.background(Theme.colors.secondPrimary),
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            coins.forEach {item ->
                DropdownMenuItem(
                    text = { Text(
                        text = item.title,
                        color = Theme.colors.title
                    ) },
                    onClick = {
                        onSelected(item)
                        expanded = false
                    },
                    leadingIcon = {
                        AsyncImage(
                            model = item.imageSrc,
                            contentDescription ="",
                            modifier = Modifier.size(36.dp),
                            contentScale = ContentScale.Crop
                        )
                    },
                    trailingIcon = {
                        Text(
                            text = "$${SharedService.formatFloat(item.price.toFloat())}",
                            color = Theme.colors.title
                        )
                    }
                )
            }
        }
    }

}