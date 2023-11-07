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
            let req = Request.OneOf_Payload.addNumber(
                AddNumber.Request.with {
                    $0.amount = num
                }
            )
            do {
                let resp = try sendRequest(request: req)
                return "OK"
            } catch ProtocolError.failure(let str) {
                return "Failure: \(str)"
            } catch ProtocolError.parsing(let str) {
                return "Fatal error: \(str)"
            }
            catch {
                return "Unknown Error: \(error.localizedDescription)"
            }
            
        } else {
            return "Not a valid number"
        }
    }
}

enum ProtocolError: Swift.Error {
    case failure(String)
    case parsing(String)
}


func sendRequest(request: Request.OneOf_Payload) throws -> Response.OneOf_Payload? {
    let req = Request.with{
        $0.payload = request
    }
    let x: Response? = try writeToWire(msg: req)
    return x.flatMap({r in r.payload})
}


func writeToWire<T: SwiftProtobuf.Message, R: SwiftProtobuf.Message>(msg: T) throws -> R? {
    let contents = try msg.serializedData()
    
    return   try contents.withUnsafeBytes { (bytes: UnsafePointer<CChar>) in
        let ba = ByteArray(size: Int32(contents.count), bytes: bytes);
        return try withUnsafePointer(to: ba, { bap in
            
            let result = ScalaKit.scala_app_request(bap)
            
            defer {
                ScalaKit.scala_app_free_result(result)
            }
            
            if let err = getError(result: result) {
                throw ProtocolError.failure(err)
            } else {
                let messageBytes = result!.pointee.message.pointee.bytes
                let messageSize = result!.pointee.message.pointee.size
                let buffer = UnsafeBufferPointer(start: messageBytes, count: Int(messageSize))
                
                do {
                    let resp = try R(serializedData: Data(buffer: buffer))
                    
                    return resp
                } catch {
                    throw ProtocolError.parsing(error.localizedDescription)
                }
            }
            
        })
    }
    
}

func getError(result: UnsafeMutablePointer<Result>?) -> String? {
    if(!ScalaKit.scala_app_result_ok(result)) {
        let errorBytes = result?.pointee.message.pointee.bytes
        let errorSize = result?.pointee.message.pointee.size
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

func initApp(options: Options) throws {
    ScalaKit.ScalaNativeInit()
    
    let contents = try options.serializedData()
    
    try contents.withUnsafeBytes { (bytes: UnsafePointer<CChar>) in
        let ba = ByteArray(size: Int32(contents.count), bytes: bytes);
        
        try withUnsafePointer(to: ba, { bap in
            
            
            let result = ScalaKit.scala_app_init(nil, bap)
            
            defer {
                ScalaKit.scala_app_free_result(result)
            }
            
            if let err = getError(result: result) {
                throw ProtocolError.failure(err)
            }
        })
    }
}



