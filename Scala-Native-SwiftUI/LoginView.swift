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
            }.font(.system(size: 15)).padding()
            
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
            switch response.payload {
            case .token(let string):
                vm.tokenAcquired(token: string, source: .login)
            case .err(let auth_error):
                errorMessage = "auth error \(auth_error)"
            case .none:
                errorMessage = "wut"
            }
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

struct ErrorView: View {
    private var errMsg: Binding<String>;
    
    init(errMsg: Binding<String>) {
        self.errMsg = errMsg
    }
    
    var body: some View {
        Label(errMsg.wrappedValue, systemImage: "cloud.rain").bold().foregroundColor(.white).frame(height: 20)
            .padding(EdgeInsets(top: 10, leading: 10, bottom: 10, trailing: 10))
            .cornerRadius(20)
            .background(Color(hex:"#8f011b"))
            .opacity(errMsg.wrappedValue.isEmpty ? 0: 1)

    }
}


#Preview {
    LoginView(vm: ViewModel())
    
}

