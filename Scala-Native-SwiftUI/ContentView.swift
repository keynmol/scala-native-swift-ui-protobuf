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
            
            
            TextField("number to add", text: $username)
            Text(greet(number: Int32($username.wrappedValue)))
        }
        .padding()
    }
    
    func greet(number: Int32?) -> String {
        if let num = number {
            let req = Request.with {
                $0.payload = Request.OneOf_Payload.addNumber(
                    AddNumber.Request.with {
                        $0.amount = num
                    }
                )
            }
            
            if let resp = writeToWire(msg: req, context: self.context!) {
                if case .Left(let error) = resp {
                    return "ERROR: \(error)"
                } else {
                    return "Great"
                }
                
            } else {
                return "Great!"
            }
        } else {
            return "Not a valid number"
        }
    }
}





func writeToWire<T: SwiftProtobuf.Message>(msg: T, context: Context) -> Either<String, SwiftProtobuf.Message>? {
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
                        let messageBytes = result!.pointee.message.pointee.bytes
                        let messageSize = result!.pointee.message.pointee.size
                        let buffer = UnsafeBufferPointer(start: messageBytes, count: Int(messageSize))
                        
                        do {
                            let resp = try Response(serializedData: Data(buffer: buffer))
                            response = resp
                        } catch {
                            
                            returnErr = error.localizedDescription
                        }
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



