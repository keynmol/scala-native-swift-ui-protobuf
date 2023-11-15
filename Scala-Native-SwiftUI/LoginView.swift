//
//  ContentView.swift
//  Scala-Native-SwiftUI
//
//  Created by Anton Sviridov on 29/10/2023.
//

import SwiftUI

struct LoginView: View {
    @SwiftUI.State private var username: String = ""
    @SwiftUI.State private var password: String = ""
    @SwiftUI.State private var errorMessage: String = ""
    
    private let vm: ViewModel;
    
    init(vm: ViewModel) {
        self.vm = vm
    }
    
    var body: some View {
        VStack {
            LogoView()
            Spacer()
            
            ErrorView(errMsg: $errorMessage)
            
            TextField("Login", text: $username).padding().fontWeight(.bold).font(.system(size: 35))
            
            SecureField("Password", text: $password).padding().fontWeight(.bold).font(.system(size: 35))
            
            
            Button(action: logIn) {
                Text("Sign in").frame(minWidth: 0, maxWidth: .infinity)
            }.styledButton().handHover()
            
            Spacer()
            
        }
        .purpleVomit()
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
    
    func logIn() {
        let resp = Interop.sendRequest(request: .login(Login.Request.with {
            $0.login = username
            $0.password = password
        }))
        
        
        if case .Ok(.login(let response)) = resp {
            vm.tokenAcquired(token: response.token, source: .login)
        }
        
        if case .Err(let msg) = resp {
            errorMessage = msg.msg()
        }
    }
}

struct LogoView: View {
    var body: some View {
        Text("Twotm8").font(.system(size: 50))
            .fontWeight(.bold).shadow(color: .black, radius: 10).foregroundColor(.white)
    }
}


//#Preview {
//    LoginView(vm: ViewModel())
//    
//}
//
