//
//  Interop.swift
//  Scala-Native-SwiftUI
//
//  Created by Anton Sviridov on 03/11/2023.
//

import Foundation
import ScalaKit
import SwiftProtobuf

struct Interop {
    enum Res {
        case Ok(Response.OneOf_Payload)
        case Err(ProtocolError)
    }
    
    enum ProtocolError: Swift.Error {
        case failure(String)
        case parsing(String)
    }
    
    
    
    static func sendRequest(request: Request.OneOf_Payload) -> Res {
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
    
    
    static func writeToWire<T: SwiftProtobuf.Message, R: SwiftProtobuf.Message>(msg: T) throws -> R? {
        let contents = try msg.serializedData()
        
        return   try contents.withUnsafeBytes { (bytes: UnsafePointer<CChar>) in
            
            let result = ScalaKit.scala_app_request(bytes, Int32(contents.count))
            
            defer {
                ScalaKit.scala_app_free_result(result)
            }
            
            if let err = getError(result: result) {
                throw ProtocolError.failure(err)
            } else {
                let messageBytes = ScalaKit.scala_app_get_data(result)
                let messageSize = ScalaKit.scala_app_get_data_length(result)
                let buffer = UnsafeBufferPointer(start: messageBytes, count: Int(messageSize))
                
                do {
                    let resp = try R(serializedData: Data(buffer: buffer))
                    
                    return resp
                } catch {
                    throw ProtocolError.parsing(error.localizedDescription)
                }
            }
            
        }
        
    }
    
    static func getError(result: ScalaResult?) -> String? {
        if(!ScalaKit.scala_app_result_ok(result)) {
            let errorBytes = ScalaKit.scala_app_get_data(result)
            let errorSize = ScalaKit.scala_app_get_data_length(result)
            let buffer = UnsafeBufferPointer(start: errorBytes, count: Int(errorSize))
            do {
                let err = try Error(serializedData: Data(buffer: buffer))
                return err.message
            } catch {
                return "Failed to deserialise error message: \(error)"
            }
        }
        else {return nil}
    }
    
    static func initApp(options: Options) throws {
        ScalaKit.ScalaNativeInit()
        
        sendRequest(request: Request.OneOf_Payload.setOptions(SetOptions.Request.with{$0.options = options}))
        
    }
    
}

extension Interop.ProtocolError {
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

