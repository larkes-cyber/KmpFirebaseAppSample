//
//  PortfolioView.swift
//  iosApp
//
//  Created by MacBook on 14.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct PortfolioView: View {
    
    let uiState:PortfolioUIState
    let onEvent:(PortfolioUIEvent) -> Void
    
    var body: some View {
        NavigationStack {
            VStack(alignment:.leading){
                Spacer()
                    .frame(height: 25)
                CommonTextField(hint: "Search", onValueChanged: { search in
                    
                })
                Spacer()
                    .frame(height: 20)
                HStack{
                    VStack(alignment:.leading){
                        VStack(alignment:.center){
                            Text("Total balance in USD")
                                .font(.system(size: 18))
                                .foregroundColor(.secondTitle)
                            Spacer()
                                .frame(height: 10)
                            if(uiState.isPortfolioInfoLoading){
                                ProgressView()
                                    .tint(Color.secondTitle)
                            }
                        }
                        Text("$\(String(format: "%.2f", uiState.portfolioInfo.portfolioSum))")
                            .font(.system(size: 34))
                            .foregroundColor(.title)
                            .fontWeight(.semibold)
                        Spacer()
                            .frame(height: 10)
                        HStack(alignment:.center){
                            Text("$\(String(format: "%.2f", uiState.portfolioInfo.moneyPortfolioProfit))")
                                .font(.system(size: 20))
                                .foregroundColor(uiState.portfolioInfo.moneyPortfolioProfit > 0 ? .priceGreen : .priceRed)
                            Spacer()
                                .frame(width: 10)
                            Text("\(String(format: "%.2f", uiState.portfolioInfo.percentagePortfolioProfit))%")
                                .font(.system(size: 20))
                                .foregroundColor(uiState.portfolioInfo.percentagePortfolioProfit > 0 ? .priceGreen : .priceRed)
                        }
                    }
                    Spacer()
                }
                Spacer()
                    .frame(height: 20)
                List(){
                    ForEach(uiState.coins, id: \.self.id){coin in
                        CoinComponent(coin: coin)
                            .listRowInsets(EdgeInsets())
                    }
                    .listRowBackground(Color.primary)
                }
                .listStyle(PlainListStyle())

            }
            .padding(.horizontal, 20)
            .scrollContentBackground(.hidden)
            .background(Color.primary)
            .toolbarBackground(Color.primary)
            .toolbar {
                ToolbarItem(placement: .navigation, content: {
                    Image("profileImage")
                        .renderingMode(.original)
                        .resizable()
                        .scaledToFill()
                        .frame(width: 50)
                        .clipShape(Circle())
                })
                
                ToolbarItem(placement: .topBarTrailing, content: {
                    Button(action: {
                        onEvent(PortfolioUIEvent.AddCoinClicked())
                    }, label: {
                        Image(systemName:"plus.circle")
                            .tint(Color.title)
                    })
                    .padding(.vertical, 8)
                    .padding(.leading, 5)
                    .padding(.trailing, 12)
                    .background(Color.secondPrimary.opacity(0.4))
                    .cornerRadius(12)
                })
            }
        }
    }
}


