//
//  TimelineView.swift
//  Scala-Native-SwiftUI
//
//  Created by Anton Sviridov on 07/11/2023.
//

import SwiftUI

struct TimelineView: View {
    @SwiftUI.State private var text: String = ""
    @SwiftUI.State private var errorMessage: String = "";
    
    @SwiftUI.State private var twots: [Twot] = [];
    
    private let vm: ViewModel;
    init(vm: ViewModel) {
        self.vm = vm
    }
    var body: some View {
        VStack(spacing: 0) {
            UserHeaderView(vm: vm)
            
            HStack{
                TextField("text", text: $text).font(.system(size: 40))
                Button(action: sendTwot) {
                    Text("RAGE").bold()
                }.disabled(text.isEmpty)
                
            }.padding().purpleVomit()
            
            HStack {
                ErrorView(errMsg: $errorMessage).background(Color.purpleVomit())
            }.frame(maxWidth: .infinity).purpleVomit()
            
            List {
                Section {
                    ForEach(twots) {
                        TwotView(twot: $0)
                    }.listRowBackground(Color.purpleVomit())
                }.purpleVomit()
                
            }.frame(maxWidth: .infinity, maxHeight: .infinity)
                .task {
                    getTwots()
                }
                .listStyle(.plain)
        }.frame(maxWidth: .infinity, maxHeight: .infinity)
    }
    
    
    func sendTwot() {}
    
    
    func getTwots() {
        if let token = vm.getToken() {
            let twots = Interop.sendRequest(request: .getWall(GetWall.Request.with {
                $0.token = token
            }))
            if case .Ok(.getWall(let wall)) = twots {
                
                switch wall.payload {
                case .wall(let wall):
                    self.twots = wall.twots
                case .err(let eRROR_CODE):
                    self.errorMessage = "error fetching twots \(eRROR_CODE)"
                case .none:
                    self.errorMessage = "error fetching twots: no response"
                }
                
            }
        }
    }
}

#Preview {
    TimelineView(vm: ViewModel())
}

