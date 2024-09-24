package com.larkes.firebasecryptoportfoliosample.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.portfolio.models.PortfolioCoinUI
import com.larkes.firebasecryptoportfoliosample.theme.Theme
import com.larkes.firebasecryptoportfoliosample.utils.SharedService

@Composable
fun CoinItem(
    modifier: Modifier = Modifier,
    portfolioCoinUI: PortfolioCoinUI
) {

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Theme.colors.secondPrimary.copy(alpha = 0.4f)),
        shape = RoundedCornerShape(20)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 17.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20))
                        .background(Theme.colors.secondPrimary)
                        .padding(10.dp)
                ) {
                    AsyncImage(
                        model = portfolioCoinUI.imageSrc,
                        contentDescription = "",
                        modifier = Modifier.size(25.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.width(15.dp))
                Column {
                    Text(
                        text = portfolioCoinUI.title,
                        color = Theme.colors.title,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "${portfolioCoinUI.shortName} • $${SharedService.formatFloat(portfolioCoinUI.currencyPrice)}",
                        color = Theme.colors.secondTitle,
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp
                    )
                }
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "$${SharedService.formatFloat(portfolioCoinUI.portfolioAmount)}",
                    color = Theme.colors.title,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "${SharedService.formatFloat(portfolioCoinUI.profit)}$",
                    color = Color(if(portfolioCoinUI.profit < 0) 0xffCA4444 else 0xff52E573).copy(alpha = 0.9f),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
            }
        }
    }

}