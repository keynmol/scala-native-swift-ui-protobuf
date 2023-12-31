//
//  TwotView.swift
//  Scala-Native-SwiftUI
//
//  Created by Anton Sviridov on 08/11/2023.
//

import SwiftUI

struct TwotView: View {
    private let twot: Twot;
    private let vm: ViewModel;
    init(twot: Twot, vm: ViewModel) {
        self.twot = twot
        self.vm = vm
    }
    
    @SwiftUI.State private var hoveringOverUsername = false;
    
    var body: some View {
        VStack(spacing: 0) {
            HStack {
                Text("@ \(twot.author)")
                    .underline(hoveringOverUsername)
                    .onHover(perform: { hovering in
                        hoveringOverUsername = hovering
                        if hovering {
                            NSCursor.pointingHand.push()
                        } else {
                            NSCursor.pop()
                        }
                    })
                    .onTapGesture {
                        vm.showProfile(name: twot.author)
                    }
                    .fontWeight(.bold)
                    .font(.system(size: 20))
                    .multilineTextAlignment(.leading)
                    .frame(maxWidth: .infinity, alignment: .leading).padding(EdgeInsets(top: 10, leading: 10, bottom: 10, trailing: 10))
            }.background(Color(hex:"#dcd79b")).frame(maxWidth: .infinity, alignment: .leading)
            
            
            HStack {
                Text(twot.text).foregroundColor(.black).frame(maxWidth: .infinity, alignment: .leading).padding().font(.system(size: 20))
            }.background(.white)
            
        }.frame(minHeight:0).cornerRadius(10).purpleVomit()
    }
}

//#Preview {
//    TwotView(twot: Twot.with {
//        $0.author = "anton"
//        $0.text = "Deallocating id 0, contains 3 pointers (total size: 2496 bytes)"
//    }, vm: ViewModel())
//}
