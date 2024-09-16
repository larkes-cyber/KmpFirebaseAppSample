//
//  AddScreen.swift
//  iosApp
//
//  Created by MacBook on 16.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AddCoinScreen: View {
    
    private let viewModel = AddCoinViewModel()
    @State private var isPortfolioPresented = false
    
    var body: some View {
        ObservingView(statePublisher: sharePublisher(viewModel.uiState), content: {uiState in
           AddCoinView(uiState: uiState, onEvent: {event in
               viewModel.onEvent(event: event)
           })
       })
        .onReceive(sharePublisher(viewModel.uiAction)){action in
            switch(action){
            case AddCoinUIAction.OpenPortfolioScreen():
                isPortfolioPresented = true
                    break
                default:
                    break
            }
        }
        .fullScreenCover(isPresented: $isPortfolioPresented){
            PortfolioScreen()
        }
    }
}

#Preview {
    AddCoinScreen()
}
