package com.larkes.firebasecryptoportfoliosample.android.screen.portfolio

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.larkes.firebasecryptoportfoliosample.android.R
import com.larkes.firebasecryptoportfoliosample.android.Theme
import com.larkes.firebasecryptoportfoliosample.android.components.CoinComponent
import com.larkes.firebasecryptoportfoliosample.android.components.LoadingSpinner
import com.larkes.firebasecryptoportfoliosample.android.components.SearchBar
import com.larkes.firebasecryptoportfoliosample.android.components.shimmerEffect
import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.portfolio.models.PortfolioUIEvent
import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.portfolio.models.PortfolioUIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortfolioView(
    uiState: PortfolioUIState,
    onEvent:(PortfolioUIEvent) -> Unit
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
                    Image(
                        painter = painterResource(id = R.drawable.profile_image),
                        contentDescription = "",
                        modifier = Modifier
                            .size(52.dp)
                            .clip(RoundedCornerShape(100)),
                        contentScale = ContentScale.Crop
                    )
                },
                title = {},
                actions = {
                    Button(
                        onClick = { onEvent(PortfolioUIEvent.AddCoinClicked) },
                        colors = ButtonDefaults.buttonColors(containerColor = Theme.colors.secondPrimary.copy(0.4f)),
                        contentPadding = PaddingValues(15.dp),
                        elevation = ButtonDefaults.buttonElevation(0.dp),
                        shape = RoundedCornerShape(20)
                    ) {
                        Icon(
                            imageVector = Icons.Sharp.Add,
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.size(27.dp)
                        )
                    }
                }
            )
        }
    ) {paddings ->
        LazyColumn(modifier = Modifier
            .padding(paddings)
            .padding(horizontal = 20.dp)){
            item {
                Spacer(modifier = Modifier.height(20.dp))
                SearchBar()
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Total balance in USD",
                    fontSize = 16.sp,
                    color = Theme.colors.secondTitle
                )
                Spacer(modifier = Modifier.height(10.dp))
                if(uiState.isCoinsLoading){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(92.dp)
                            .clip(RoundedCornerShape(20))
                            .shimmerEffect()
                    )
                }else{
                    Text(
                        text = "$${String.format("%.2f", uiState.portfolioInfo.portfolioSum)}",
                        color = Theme.colors.title,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 38.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "$${String.format("%.2f", uiState.portfolioInfo.moneyPortfolioProfit)}",
                            color = Color(if(uiState.portfolioInfo.moneyPortfolioProfit < 0) 0xffCA4444 else 0xff52E573).copy(alpha = 0.9f),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "${String.format("%.2f", uiState.portfolioInfo.percentagePortfolioProfit)}%",
                            color = Color(if(uiState.portfolioInfo.percentagePortfolioProfit < 0) 0xffCA4444 else 0xff52E573).copy(alpha = 0.9f),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }

                }
                Spacer(modifier = Modifier.height(20.dp))
            }
            items(uiState.coins, {it.id}){coin ->
                CoinComponent(
                    portfolioCoinUI = coin
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                if(uiState.isCoinsLoading){
                    Spacer(modifier = Modifier.height(8.dp))
                    LoadingSpinner()
                }
            }


        }

    }


}