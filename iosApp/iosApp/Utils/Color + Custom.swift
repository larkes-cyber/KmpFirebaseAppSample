//
//  Color + Custom.swift
//  iosApp
//
//  Created by MacBook on 16.08.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

extension Color{
    
    static let action = Color("Action")
    static let primary = Color("Primary")
    static let secondPrimary = Color("SecondPrimary")
    static let secondTitle = Color("SecondTitle")
    static let title = Color("Title")
    static let priceRed = Color("PriceRed")
    static let priceGreen = Color("PriceGreen")

    init(hex: UInt, alpha: Double = 1) {
      self.init(
          .sRGB,
          red: Double((hex >> 16) & 0xff) / 255,
          green: Double((hex >> 08) & 0xff) / 255,
          blue: Double((hex >> 00) & 0xff) / 255,
          opacity: alpha)
    }
    
}
