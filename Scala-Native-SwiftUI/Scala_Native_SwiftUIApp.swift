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
    init() {
        do {
            try initApp(options: Options.with {
                $0.debugLogging = true
            })
        } catch {
            
        }
        
        
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
