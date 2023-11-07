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
    
    func greet(number: Int32?) -> AttributedString {
        var str = AttributedString()
        if let num = number {
            let req = Request.OneOf_Payload.addNumber(
                AddNumber.Request.with {
                    $0.amount = num
                }
            )
            switch sendRequest(request: req) {
            case .Ok: do {
                var str = AttributedString("OK")
                str.foregroundColor = .green
                return str
            }
            case .Err(let err): do {
                var str = AttributedString(err.msg())
                str.foregroundColor = .red
                return str
            }
            }
        } else {
            //return "Not a valid number"
            var str = AttributedString("Not a valid number")
            str.foregroundColor = .red
            return str
        }
    }
}

enum Res {
    case Ok(Response.OneOf_Payload)
    case Err(ProtocolError)
}

enum ProtocolError: Swift.Error {
    case failure(String)
    case parsing(String)
}

extension ProtocolError {
    func msg() -> String {
        let str = if case .failure(let string) = self {
            string
        } else if case .parsing(let string) = self {
             string
        } else {
             ""
        }
        
        return str
    }
}


func sendRequest(request: Request.OneOf_Payload) -> Res {
    let req = Request.with{
        $0.payload = request
    }
    
    do {
        let x: Response? = try writeToWire(msg: req)
        if let resp = x {
            if let payload = resp.payload {
                return Res.Ok(payload)
            } else {
                return Res.Err(ProtocolError.parsing("response payload cannot be empty"))
            }
            
        } else {
            return Res.Err(ProtocolError.parsing("empty response"))
        }
    } catch let e as ProtocolError {
        return Res.Err(e)
    } catch {
        return Res.Err(ProtocolError.failure("Unknown error: \(error.localizedDescription)"))
    }
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

#Preview {

    ContentView()

}

