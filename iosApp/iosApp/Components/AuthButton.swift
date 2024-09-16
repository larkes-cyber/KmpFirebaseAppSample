//
//  AuthButton.swift
//  iosApp
//
//  Created by MacBook on 14.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import FirebaseCore
import GoogleSignIn

struct AuthButton: View {
    
    let onSuccess:(String, String) -> Void
    
    var body: some View {
        Button(action: {
            makeGoogleSignInRequest()
        }, label: {
            Text("Google")
                .font(.system(size: 20))
                .foregroundColor(.white)
        })
        .frame(maxWidth: .infinity, maxHeight: 55)
        .background(Color.blue)
        .cornerRadius(10)
    }
    
    private func makeGoogleSignInRequest(){
              guard let clientID = FirebaseApp.app()?.options.clientID else { return }
              
              let config = GIDConfiguration(clientID: clientID)
              GIDSignIn.sharedInstance.configuration = config
              
              GIDSignIn.sharedInstance
               
               .signIn(withPresenting: Application_utility.rootController) { user, error in
                  
                  if let error = error{

                      return
                  }
               
                  let user = user?.user
                  let idToken = user!.idToken!
                   let accessToken = user?.accessToken.tokenString
                   onSuccess(idToken.tokenString, accessToken!)

              }
          }
}

