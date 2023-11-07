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
    @SwiftUI.State private var error: String? = nil
    
    private var switchView: (ViewModel.State) -> ();
    
    init(switchView: @escaping (ViewModel.State) -> ()) {
        self.switchView = switchView
    }
    
    var body: some View {
        VStack {
            Spacer()
            if let errMsg = error {
                Label(errMsg, systemImage: "cloud.rain").bold().foregroundColor(.white).frame(height: 20)
                    .padding(EdgeInsets(top: 10, leading: 10, bottom: 10, trailing: 10))
                    .cornerRadius(20)
                    .background(Color(hex:"#8f011b"))
            } else {
                Spacer(minLength: 40)
            }
            
            
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
        let resp = Interop.sendRequest(request: .login(Login.Request.with {
            $0.login = username
            $0.password = password
        }))
        
        switch resp {
        case .Ok(let oneOf_Payload):
            print(oneOf_Payload)
        case .Err(let protocolError):
            error = protocolError.msg()
        }
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

