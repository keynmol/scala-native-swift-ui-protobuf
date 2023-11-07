//
//  TimelineView.swift
//  Scala-Native-SwiftUI
//
//  Created by Anton Sviridov on 07/11/2023.
//

import SwiftUI

struct TimelineView: View {
    @SwiftUI.State private var text: String = ""
    var body: some View {
        VStack {
            HStack{
                TextField("text", text: $text).font(.system(size: 40))
                Button(action: sendTwot) {
                    Text("RAGE")
                    
                }.font(.system(size: 20))
            }.padding()
            
            Spacer()
        }
        .background(Color(hex:"#9ba0dc"))
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
    
    func sendTwot() {}
}

#Preview {
    TimelineView()
}
