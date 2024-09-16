//
//  CoinComponent.swift
//  iosApp
//
//  Created by MacBook on 14.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CoinComponent: View {
    
    let coin:PortfolioCoinUI
    init(coin: PortfolioCoinUI) {
        self.coin = coin
    }
    
    var body: some View {
        VStack{
            ZStack{
                Color.secondPrimary.opacity(0.4)
                HStack{
                    HStack{
                        ZStack{
                            Color.secondPrimary
                            AsyncImage(url: URL(string: coin.imageSrc)){image in
                                image.image?.resizable().frame(width:35, height: 35)
                            }
                        }
                        .frame(width: 50, height: 50)
                        .clipShape(.rect(cornerRadius: 10))
                        VStack(alignment:.leading){
                            Text(coin.title)
                                .font(.system(size: 18))
                                .foregroundColor(.title)
                            Spacer()
                                .frame(height: 5)
                            Text("\(coin.shortName) • $\(String(format: "%.2f", coin.currencyPrice))")
                                .font(.system(size: 16))
                                .foregroundColor(.secondTitle)
                        }
                    }
                    Spacer()
                    VStack(alignment: .trailing){
                        Text("$\(String(format: "%.2f", coin.portfolioAmount))")
                            .font(.system(size: 20))
                            .foregroundColor(.title)
                        Spacer()
                            .frame(height: 5)
                        Text("\(String(format: "%.2f", coin.profit))%")
                            .font(.system(size: 18))
                            .foregroundColor(coin.profit > 0 ? .priceGreen : .priceRed)
                    }
                }
                .padding(.horizontal, 15)
                .padding(.vertical, 15)
            }
            .cornerRadius(15)
            Spacer()
                .frame(height: 8)
        }
    }
}

