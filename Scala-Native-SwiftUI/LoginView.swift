//
//  ContentView.swift
//  Scala-Native-SwiftUI
//
//  Created by Anton Sviridov on 29/10/2023.
//

import SwiftUI
import ScalaKit

struct LoginView: View {
    @SwiftUI.State private var username: String = ""
    @SwiftUI.State private var password: String = ""
    
    private var switchView: (ViewModel.State) -> ();
    
    init(switchView: @escaping (ViewModel.State) -> ()) {
        self.switchView = switchView
    }
    
    var body: some View {
        VStack {
            Spacer()
            Text("Twotm8").font(.system(size: 50))
                .fontWeight(.bold).shadow(color: .black, radius: 10).foregroundColor(.white)
            
            TextField("Login", text: $username).padding().fontWeight(.bold).font(.system(size: 35))
            
            SecureField("Password", text: $password).padding().fontWeight(.bold).font(.system(size: 35))
            
            
            Button(action: logIn) {
                Text("Sign in").frame(minWidth: 0, maxWidth: .infinity)
            }.font(.system(size: 15)).padding()
            
            Spacer()
            
        }
        .background(Color(hex:"#9ba0dc"))
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
    
    func logIn() {
        switchView(.timeline)
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


#Preview {
    LoginView(switchView: {(v: ViewModel.State) in ()})
}

