//
//  ErrorView.swift
//  Scala-Native-SwiftUI
//
//  Created by Anton Sviridov on 12/11/2023.
//

import SwiftUI


struct ErrorView: View {
    private var errMsg: Binding<String>;
    
    init(errMsg: Binding<String>) {
        self.errMsg = errMsg
    }
    
    var body: some View {
        Label(errMsg.wrappedValue, systemImage: "cloud.rain").bold().foregroundColor(.white).frame(height: 20)
            .padding(EdgeInsets(top: 10, leading: 10, bottom: 10, trailing: 10))
            .cornerRadius(20)
            .background(Color(hex:"#8f011b"))
            .opacity(errMsg.wrappedValue.isEmpty ? 0: 1)
            .frame(height: errMsg.wrappedValue.isEmpty ? 0 : 40)

    }
}
//
//#Preview {
//    ErrorView(errMsg: Binding.constant("some error message"))
//}
