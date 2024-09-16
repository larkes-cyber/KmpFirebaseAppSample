//
//  PortfolioScreen.swift
//  iosApp
//
//  Created by MacBook on 14.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct PortfolioScreen: View {
    
    private let viewModel = PortfolioViewModel()
    @State private var isAddScreenPresented = false
    
    var body: some View {
        ObservingView(statePublisher: sharePublisher(viewModel.uiState), content: {uiState in
           PortfolioView(uiState: uiState, onEvent: {event in
               viewModel.onEvent(event: event)
           })
       })
        .onReceive(sharePublisher(viewModel.uiAction)){action in
            switch(action){
            case PortfolioUIAction.OpenAddCoinScreen():
                isAddScreenPresented = true
                    break
                default:
                    break
            }
        }
        .fullScreenCover(isPresented: $isAddScreenPresented){
            AddCoinScreen()
        }
    }
}

#Preview {
    PortfolioScreen()
}
