package com.larkes.firebasecryptoportfoliosample.features.portfolio.ui.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.larkes.firebasecryptoportfoliosample.components.AddCoinTextField
import com.larkes.firebasecryptoportfoliosample.components.CoinsDropDownWrapper
import com.larkes.firebasecryptoportfoliosample.components.LoadingSpinner
import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.add.models.AddCoinUIEvent
import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.add.models.AddCoinUIState
import com.larkes.firebasecryptoportfoliosample.theme.Theme
import com.larkes.firebasecryptoportfoliosample.utils.SharedService

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCoinView(
    uiState: AddCoinUIState,
    onEvent:(AddCoinUIEvent) -> Unit
) {

    Scaffold(
        containerColor = Theme.colors.primary,
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .padding(horizontal = 20.dp),
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Theme.colors.primary),
                navigationIcon = {

                    Button(
                        onClick = { onEvent(AddCoinUIEvent.ArrowBackClicked) },
                        colors = ButtonDefaults.buttonColors(containerColor = Theme.colors.secondPrimary),
                        contentPadding = PaddingValues(15.dp),
                        elevation = ButtonDefaults.buttonElevation(0.dp),
                        shape = RoundedCornerShape(20)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.size(27.dp)
                        )
                    }
                },
                title = {
                    Text(
                        text = "Add coin",
                        color = Theme.colors.title,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 26.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                actions = {
                    Button(
                        onClick = { onEvent(AddCoinUIEvent.DoneClicked) },
                        colors = ButtonDefaults.buttonColors(containerColor = Theme.colors.secondPrimary),
                        contentPadding = PaddingValues(15.dp),
                        elevation = ButtonDefaults.buttonElevation(0.dp),
                        shape = RoundedCornerShape(20)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.size(27.dp)
                        )
                    }
                }
            )
        }
    ) {paddings ->

        if(uiState.selectedCoin != null){
            LazyColumn(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(paddings)
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ){
                item {
                    Text(
                        text = "Select coin",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Theme.colors.title
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    CoinsDropDownWrapper(
                        selectedCoin = uiState.selectedCoin,
                        coins = uiState.availableCoins,
                        onSelected = {

                            onEvent(AddCoinUIEvent.CurrencySelected(it))
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item{
                    Text(
                        text = "Currency price",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Theme.colors.title
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    AddCoinTextField(
                        hint = "0.0",
                        modifier = Modifier.fillMaxWidth(),
                        text = if(uiState.currencyPrice != null) SharedService.formatFloat(uiState.currencyPrice.toFloat()) else "",
                        onChange = {
                            onEvent(AddCoinUIEvent.CurrencyPriceTyped(it))
                        }
                    )
                }
                item{
                    Text(
                        text = "Amount",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Theme.colors.title
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    AddCoinTextField(
                        hint = "0.00",
                        modifier = Modifier.fillMaxWidth(),
                        text =if(uiState.amount != null  ) SharedService.formatFloat(uiState.amount) else "",
                        onChange = {
                            onEvent(AddCoinUIEvent.AmountTyped(it))
                        }
                    )
                }

            }
        }else{
            Box(modifier = Modifier.padding(paddings).padding(horizontal = 20.dp).padding(top = 15.dp)){
                LoadingSpinner()
            }
        }


    }

}