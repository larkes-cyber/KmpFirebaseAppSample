//
//  AuthView.swift
//  iosApp
//
//  Created by MacBook on 14.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import FirebaseCore
import GoogleSignIn
import shared

struct AuthView: View {
    
    let onEvent:(AuthUIEvent) -> Void
    
    var body: some View {
        ZStack{
            Color.primary
            VStack(alignment: .leading){
                Image(.logoImage1)
                    .resizable()
                    .scaledToFill()
                    .frame(maxWidth: .infinity, maxHeight: 250)
                
                Spacer()
                    .frame(height: 35)
                
                Text("Start your crypto portfolio")
                    .font(.system(size: 36))
                    .fontWeight(.semibold)
                    .foregroundColor(.title)
                Spacer()
                    .frame(height: 30)
                Text("Take your investment portfolio to next level")
                    .font(.system(size: 18))
                    .fontWeight(.medium)
                    .foregroundColor(.secondTitle)
                Spacer()
                    .frame(height: 40)
                
                AuthButton(
                    onSuccess: {idToken, accessToken in
                        onEvent(AuthUIEvent.SignInSucceeded(idToken: idToken, accessToken: accessToken))
                    }
                )
            }
        
            .padding(.horizontal, 20)
        }
        .ignoresSafeArea()
    }


}


