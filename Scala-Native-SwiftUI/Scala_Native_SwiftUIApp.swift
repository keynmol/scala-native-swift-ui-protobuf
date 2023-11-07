//
//  Scala_Native_SwiftUIApp.swift
//  Scala-Native-SwiftUI
//
//  Created by Anton Sviridov on 29/10/2023.
//

import SwiftUI
import ScalaKit

@main
struct Scala_Native_SwiftUIApp: App {
    @ObservedObject var vm = ViewModel()
    init() {
        do {
            try Interop.initApp(options: Options.with {
                $0.debugLogging = true
            })
        } catch {
            
        }
        
        
    }
    
    func setView(state: ViewModel.State) {
        vm.state = state
    }
    
    var body: some Scene {
        WindowGroup {
            switch vm.state {
            case .logIn:
                LoginView(switchView: setView)
            case .timeline:
                TimelineView()
            case .showProfile(let string):
                LoginView(switchView: setView)
            }
            
        }
    }
}
