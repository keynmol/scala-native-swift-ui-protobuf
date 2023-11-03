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
    @SwiftUI.State private var username: String = ""
    private var context: Context? = nil
    init(context: Context) {
        self.context = context
    }
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
    
    func greet(username: String) -> String {
        let req = Request.with {
            
            $0.payload = Request.OneOf_Payload.addNumber(
                AddNumber.Request.with {
                    $0.amount = -25
                }
            )
        }
        
        
        
        print(writeToWire(msg: req, context: self.context!))

        return "yo"
    }
}





func writeToWire<T: SwiftProtobuf.Message>(msg: T, context: Context) -> Either<String, SwiftProtobuf.Message> {
    do {
        let contents = try msg.serializedData()
        var response: SwiftProtobuf.Message? = nil
        var returnErr: String? = nil
        
        try contents.withUnsafeBytes { (bytes: UnsafePointer<CChar>) in
            let ba = ByteArray(size: Int32(contents.count), bytes: bytes);
             withUnsafePointer(to: ba, { bap in
                withUnsafePointer(to: context, {contextPtr in
                    
                    let result = ScalaKit.scala_app_request(bap, contextPtr)
                    if let err = getError(result: result) {
                        returnErr = err
                    } else {
                        
                    }

                })
            })
        }
        
        
        if let err = returnErr {
            return Either.Left(err)
        } else {
            
            return Either.Right(msg)
        }
        
        
        
    } catch {
        return Either.Left(error.localizedDescription)
    }
 
}

enum Either<L, R> {
    case Left(L), Right(R)
}


func getError(result: UnsafeMutablePointer<Result>?) -> String? {
    if(!ScalaKit.scala_app_result_ok(result)) {
        let errorBytes = result?.pointee.error.pointee.bytes
        let errorSize = result?.pointee.error.pointee.size
        let buffer = UnsafeBufferPointer(start: errorBytes, count: Int(errorSize!))
        do {
            let err = try Error(serializedData: Data(buffer: buffer))
            return err.message
        } catch {
            return "Failed to deserialise error message: \(error)"
        }
    }
    else {return nil}

}

func initApp(context: Context) {
    ScalaKit.ScalaNativeInit()
    
    let result = withUnsafePointer(to: context, {contextPtr in ScalaKit.scala_app_init(nil, contextPtr)})
    
    print(getError(result: result))
}



