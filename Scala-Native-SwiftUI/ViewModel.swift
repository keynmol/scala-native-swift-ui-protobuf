//
//  ViewModel.swift
//  Scala-Native-SwiftUI
//
//  Created by Anton Sviridov on 07/11/2023.
//

import Foundation

class ViewModel: ObservableObject {
    @Published var state: State = .logIn
    
    // some code to update state...
}
extension ViewModel {
    enum State {
        case logIn, timeline, showProfile(String)
    }
}
