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
        
    }
    
    func setView(state: ViewModel.State) {
        vm.state = state
    }
    
    var body: some Scene {
        
        WindowGroup {
            switch vm.state {
            case .logIn:
                LoginView(vm: self.vm)
            case .timeline:
                TimelineView(vm: self.vm)
            case .showProfile(let string):
                LoginView(vm: self.vm)
            }
            
        }
    }
}
