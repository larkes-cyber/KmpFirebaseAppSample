package com.larkes.firebasecryptoportfoliosample.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.larkes.firebasecryptoportfoliosample.theme.Theme

@Composable
fun SearchBar() {

    TextField(
        modifier = Modifier
        //    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
            .fillMaxWidth()
            .height(56.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Theme.colors.secondPrimary.copy(0.4f),
            disabledContainerColor = Theme.colors.secondPrimary.copy(0.4f),
            cursorColor = Theme.colors.action,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Theme.colors.secondPrimary.copy(0.4f)
        ),
        placeholder = {
            Row {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier.size(24.dp),
                    tint = Theme.colors.secondTitle
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Search",
                    color = Theme.colors.secondTitle,
                    fontSize = 15.sp
                )
            }
        },
        value = "",
        shape = RoundedCornerShape(10.dp),
        onValueChange = {
        })

}