//
//  ViewModel.swift
//  Scala-Native-SwiftUI
//
//  Created by Anton Sviridov on 07/11/2023.
//

import Foundation

enum TokenAction {
    case login
    case defaults
}

class ViewModel: ObservableObject {
    private var token: String?;
    private var nickname: String?;
    private var id: String?;
    
    @Published var state: State = .logIn
    
    init() {
        do {
            try Interop.initApp(options: Options.with {
                $0.debugLogging = true
            })
        } catch {
            
        }
        let savedToken =
            UserDefaults.standard.string(forKey: "twotm8_token")
        
        if let token = savedToken {
            tokenAcquired(token: token, source: .defaults)
        } else {
            logOut()
        }
    }
    
    func tokenAcquired(token: String, source: TokenAction) {
        func saveToken(token:String) {
            self.token = token
            UserDefaults.standard.set(token, forKey: "twotm8_token")
            state = .timeline
        }
        func checkAndSaveToken(token: String) {
            let resp = Interop.sendRequest(request: .getMe(GetMe.Request.with {
                $0.token = token
            }))
            
            if case .Ok(.getMe(let bla)) = resp {
                saveToken(token: token)
                nickname = bla.me.nickname
                id = bla.me.id
            }
        }
        
        switch source {
        case .login:
            checkAndSaveToken(token: token)
        case .defaults:
            checkAndSaveToken(token: token)
        }
        
    }
    
    func logOut() {
        self.token = nil
        self.nickname = nil
        self.id = nil
        UserDefaults.standard.set(nil, forKey: "twotm8_token")
        state = .logIn
    }
    
    func getToken() -> String? {
        token
    }
    
    func getNickname() -> String? {
        nickname
    }
    
    func getMyId() -> String? {
        id
    }
}


extension ViewModel {
    enum State {
        case logIn, timeline, showProfile(String)
    }
}
