//
//  AuthScreen.swift
//  iosApp
//
//  Created by MacBook on 14.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AuthScreen: View {
    
    private let viewModel = AuthViewModel()
    @State private var isPortoflioPresented = false
    
    var body: some View {
        AuthView(){event in
            viewModel.onEvent(event: event)
        }
        .onReceive(sharePublisher(viewModel.uiAction)){action in
            switch(action){
            case AuthUIAction.OpenPortfolioScreen():
                isPortoflioPresented = true
                    break
                default:
                    break
            }
        }
        .fullScreenCover(isPresented: $isPortoflioPresented){
            PortfolioScreen()
        }
    }
}

#Preview {
    AuthScreen()
}
