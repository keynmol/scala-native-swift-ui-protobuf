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
    
    private var context: Context? = nil
    
    init() {
        let context = ScalaKit.Context(allocator: {size in
            UnsafeMutableRawPointer(mutating: UnsafeRawPointer(UnsafeMutablePointer<CChar>.allocate(capacity: size)))})
        initApp(context: context)
        
        self.context = context
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView(context: self.context!)
        }
    }
}
