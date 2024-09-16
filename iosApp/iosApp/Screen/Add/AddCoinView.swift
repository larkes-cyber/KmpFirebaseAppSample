//
//  AddView.swift
//  iosApp
//
//  Created by MacBook on 16.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AddCoinView: View {
    
    let uiState:AddCoinUIState
    let onEvent:(AddCoinUIEvent) -> Void
    
    
    init(uiState: AddCoinUIState, onEvent: @escaping (AddCoinUIEvent) -> Void) {
        self.uiState = uiState
        self.onEvent = onEvent
    }
    
    var body: some View {
        NavigationStack {
            ZStack{
                Spacer()
                VStack{
                    VStack(
                        alignment:.leading,
                        spacing: 15
                    ){
                        if(uiState.selectedCoin != nil){
                            Text("Select coin")
                                .font(.system(size: 18))
                                .foregroundColor(.title)
                            
                            CoinPicker(selectedCoin: uiState.selectedCoin!, coins: uiState.availableCoins, onSelected: {item in
                                onEvent(AddCoinUIEvent.CurrencySelected(coinUI: item))
                            })
                            
                            Text("Currency price")
                                .font(.system(size: 18))
                                .foregroundColor(.title)
                            CommonTextField(
                                hint: "0.0",
                                onValueChanged: {value in
                                    onEvent(AddCoinUIEvent.CurrencyPriceTyped(price: value))
                                }
                            )
                            
                            Text("Amount")
                                .font(.system(size: 18))
                                .foregroundColor(.title)
                            CommonTextField(
                                hint: "0.0",
                                onValueChanged: {value in
                                    onEvent(AddCoinUIEvent.AmountTyped(amount: value))
                                }
                            )
                        }
                    }
                    Spacer()
                }
            }
            .frame(maxWidth: .infinity, maxHeight:.infinity)
            .padding(.horizontal, 20)
            .background(Color.primary)
            .scrollContentBackground(.hidden)
            .toolbarBackground(Color.primary)
            .toolbar {
                ToolbarItem(placement: .topBarLeading, content: {
                    Button(action: {
                        onEvent(AddCoinUIEvent.ArrowBackClicked())
                    }, label: {
                        Image(systemName:"arrow.backward")
                            .tint(Color.title)
                    })
                    .padding(10)
                    .background(Color.secondPrimary.opacity(0.4))
                    .cornerRadius(12)
                })
                
                ToolbarItem(placement: .principal, content: {
                    Text("Add coin")
                        .font(.system(size: 26))
                        .foregroundColor(.title)
                })
                
                ToolbarItem(placement: .topBarTrailing, content: {
                    Button(action: {
                        onEvent(AddCoinUIEvent.DoneClicked())
                    }, label: {
                        Image(systemName:"checkmark")
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

