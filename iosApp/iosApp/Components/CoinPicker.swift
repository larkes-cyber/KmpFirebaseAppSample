//
//  CoinPicker.swift
//  iosApp
//
//  Created by MacBook on 16.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CoinPicker: View {
    
    let selectedCoin:CoinUI
    let coins:[CoinUI]
    let onSelected:(CoinUI) -> Void
    init(selectedCoin: CoinUI, coins: [CoinUI], onSelected: @escaping (CoinUI) -> Void) {
        self.selectedCoin = selectedCoin
        self.coins = coins
        self.onSelected = onSelected
    }
    
    var body: some View {
        Menu(selectedCoin.shortName) {
            ForEach(coins, id: \.self) { item in
                Button(action: {
                  onSelected(item)
                }, label: {
                    Text(item.shortName)
                        .foregroundStyle(Color.white)
                        .background(RoundedRectangle(cornerRadius: 16).fill(Color.black))
                })
            }
        }
        .padding(.all, 16)
        .foregroundStyle(Color.white)
        .frame(maxWidth: .infinity)
        .background(RoundedRectangle(cornerRadius: 10).fill(Color.secondPrimary.opacity(0.4)))

    }
}


