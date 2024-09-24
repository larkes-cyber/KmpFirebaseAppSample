package com.larkes.firebasecryptoportfoliosample.components

import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.larkes.firebasecryptoportfoliosample.theme.Theme

@Composable
fun AddCoinTextField(
    modifier: Modifier = Modifier,
    hint:String,
    text:String,
    onChange:(String) -> Unit
) {

    OutlinedTextField(
        value = text,
        onValueChange = {
            onChange(it)
        },
        singleLine = true,
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Theme.colors.primary,
            focusedContainerColor =  Theme.colors.primary,
            unfocusedTextColor = Theme.colors.title,
            focusedTextColor = Theme.colors.title,
            focusedIndicatorColor = Theme.colors.title.copy(alpha = 0.6f),
            unfocusedIndicatorColor = Theme.colors.title,
            unfocusedTrailingIconColor = Theme.colors.title,
            focusedTrailingIconColor = Theme.colors.title
        ),
        placeholder = {
            if(text.isEmpty()){
                Text(
                    text = hint,
                    color = Theme.colors.title
                )
            }
        }
    )

}