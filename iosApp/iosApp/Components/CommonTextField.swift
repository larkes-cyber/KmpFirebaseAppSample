//
//  CommonTextField.swift
//  iosApp
//
//  Created by MacBook on 14.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct CommonTextField: View {
    
    @State private var value = ""
    private let hint:String
    private let enabled:Bool
    private let onValueChanged:(String) -> Void
    
    init(hint: String,enabled:Bool = true, onValueChanged:@escaping (String) -> Void) {
        self.hint = hint
        self.enabled = enabled
        self.onValueChanged = onValueChanged

    }
    
    var body: some View {
        ZStack(alignment:.leading){
            RoundedRectangle(cornerRadius: 10.0)
                .foregroundColor(.secondPrimary.opacity(0.4))
            
            if(value.isEmpty){
                Text(hint)
                    .foregroundColor(Color.secondTitle)
                    .padding(.vertical, 13)
                    .padding(.horizontal, 20)
                    .font(.system(size: 16))
            }
            
            TextField("", text: $value)
                .foregroundColor(Color.secondTitle)
                .font(.system(size: 16))
                .autocapitalization(.none)
                .disableAutocorrection(true)
                .padding(EdgeInsets(top: 0, leading: 20, bottom: 0, trailing: 20))
                .disabled(!self.enabled)
                .onChange(of: value){ newValue in
                    onValueChanged(newValue)
                }
            
        }
        .frame(maxHeight: 55)
    }
}
