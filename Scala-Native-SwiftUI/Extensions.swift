//
//  Extensions.swift
//  Scala-Native-SwiftUI
//
//  Created by Anton Sviridov on 09/11/2023.
//

import Foundation
import SwiftUI

extension Twot: Identifiable {
    
}

extension Color {
    static func purpleVomit() -> Color {
        Color(hex: "#9ba0dc")
    }
}

extension View {
    func purpleVomit() -> some View {
        self.background(Color(hex: "#9ba0dc"))
    }
}


extension Color {
    init(hex: String) {
        let hex = hex.trimmingCharacters(in: CharacterSet(charactersIn: "#"))
        let rgbValue = UInt32(hex, radix: 16)
        let r = Double((rgbValue! & 0xFF0000) >> 16) / 255
        let g = Double((rgbValue! & 0x00FF00) >> 8) / 255
        let b = Double(rgbValue! & 0x0000FF) / 255
        self.init(red: r, green: g, blue: b)
    }
}
