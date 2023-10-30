//
//  ContentView.swift
//  Scala-Native-SwiftUI
//
//  Created by Anton Sviridov on 29/10/2023.
//

import SwiftUI
import ScalaKit
import SwiftProtobuf

struct ContentView: View {
    @State private var username: String = ""
    var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundColor(.accentColor)
            
            
            TextField("Your name:", text: $username)
            Text(greet(username: username))
        }
        .padding()
    }
}

func greet(username: String) -> String {
    ScalaKit.ScalaNativeInit()
    
    let msg = ScalaApp_Messages_Exchange.with{
        $0.payload = ScalaApp_Messages_Exchange.OneOf_Payload.addString(
            ScalaApp_Messages_AddString.with {
                $0.payload = ScalaApp_Messages_AddString.OneOf_Payload.request(
                    ScalaApp_Messages_AddString.Request.with {
                    $0.value = username
                })
            }
        )
    }
    
    writeToWire(msg: msg)

    return "yo"
}

func writeToWire<T: SwiftProtobuf.Message>(msg: T) -> Void {
    
    do {
        let contents = try msg.serializedData()
        try contents.withUnsafeBytes<Void, CChar>{ (bytes: UnsafePointer<CChar>) in
            let ba = ByteArray(size: Int32(contents.count), bytes: bytes);
            withUnsafePointer(to: ba, { bap in
                ScalaKit.scala_app_notification(bap)
            })
            
        }
        return ()
    } catch {
        
    }
    
    return ()
    
}

#Preview {
    ContentView()
}
