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
    
    @SwiftUI.State private var loading = false;
    
    @State var lastRefreshed = Date.now
    let timer = Timer.publish(every: 5, on: .main, in: .common).autoconnect()
    
    private let vm: ViewModel;
    init(vm: ViewModel) {
        self.vm = vm
    }
    var body: some View {
        VStack(spacing: 0) {
            UserHeaderView(vm: vm)
            
            HStack{
                TextField("text", text: $text).font(.system(size: 20))
                Button(action: sendTwot) {
                    Text("RAGE").bold()
                }.disabled(text.isEmpty)
                
            }.padding().purpleVomit()
            
            HStack {
                ErrorView(errMsg: $errorMessage).background(Color.purpleVomit())
            }.frame(maxWidth: .infinity).purpleVomit()
            
            SpinnerView(loading: $loading).padding(EdgeInsets())
            
            List {
                Section {
                    ForEach(twots) {
                        TwotView(twot: $0, vm: self.vm)
                    }.listRowBackground(Color.purpleVomit())
                }.purpleVomit()
                
            }.frame(maxWidth: .infinity, maxHeight: .infinity)
                .task {
                    getTwots()
                }
                .onReceive(timer) { input in
                    if lastRefreshed.distance(to: input) >= 5 {
                        getTwots()
                    }
                }
                .listStyle(.plain)
            Spacer()
        }.frame(maxWidth: .infinity, maxHeight: .infinity).purpleVomit()
        
        
    }
    
    
    func sendTwot() {
        if let token = vm.getToken() {
            self.loading = true
            defer {
                self.loading = false
            }

            let resp = Interop.sendRequest(request: .sendTwot(SendTwot.Request.with {
                $0.text = self.text
                $0.token = token
            }))
            
            switch resp {
            case .Ok(let _):
                self.text = ""
                getTwots()
            case .Err(let protocolError):
                self.errorMessage = protocolError.msg()
            }
        }
        
    }
    
    
    func getTwots() {
        if let token = vm.getToken() {
            self.loading = true
            defer {
                self.loading = false
            }
            
            let twots = Interop.sendRequest(request: .getWall(GetWall.Request.with {
                $0.token = token
            }))
            if case .Ok(.getWall(let wall)) = twots {
                self.twots = wall.wall.twots
                lastRefreshed = Date.now
            }
        }
    }
}

#Preview {
    TimelineView(vm: ViewModel())
}

