//
//  Application_utility.swift
//  iosApp
//
//  Created by MacBook on 16.08.2024.
//  Copyright © 2024 orgName. All rights reserved.
//


import SwiftUI
import UIKit


final class Application_utility{
    
    static var rootController:UIViewController{
        guard let screen = UIApplication.shared.connectedScenes.first as? UIWindowScene else{
            return .init()
        }
        
        guard let root = screen.windows.first?.rootViewController else {
            return .init()
        }
        
        return root
        
    }
    
}
