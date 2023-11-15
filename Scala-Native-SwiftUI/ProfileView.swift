//
//  ProfileView.swift
//  Scala-Native-SwiftUI
//
//  Created by Anton Sviridov on 13/11/2023.
//

import SwiftUI

struct ProfileView: View {
    @SwiftUI.State private var twots: [Twot] = [];
    
    @SwiftUI.State private var loading = false;
    
    
    @State var lastRefreshed = Date.now
    let timer = Timer.publish(every: 5, on: .main, in: .common).autoconnect()
    
    private let vm: ViewModel;
    init( vm: ViewModel) {
        self.vm = vm
    }
    var body: some View {
        VStack(spacing: 0) {
            UserHeaderView(vm: vm)
            
            SpinnerView(loading: $loading).padding(EdgeInsets())
            
            Button("← Back to timeline", action: goBack)
                .styledButton()
                .handHover()
            
            List {
                Section {
                    ForEach(twots) {
                        TwotView(twot: $0, vm: self.vm)
                    }.listRowBackground(Color.purpleVomit())
                }.purpleVomit()
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .task {
                getTwots()
            }
            .onReceive(timer) { input in
                if lastRefreshed.distance(to: input) >= 5 {
                    getTwots()
                }
            }.listStyle(.plain)
                .purpleVomit()
        }.purpleVomit()
    }
    
    func goBack() {
        self.vm.state = .timeline
    }
    
    func getTwots() {
        self.loading = true
        defer {
            self.loading = false
        }
        
        switch vm.state {
        case .showProfile(let profile):
            let twots = Interop.sendRequest(request: .getThoughtLeader(GetThoughtLeader.Request.with {
                $0.nickname = profile
            }))
            if case .Ok(.getThoughtLeader(let tl)) = twots {
                self.twots = tl.thoughtLeader.twots
                lastRefreshed = Date.now
            }
        case _:
            vm.state = .timeline
        }
        
        
        
        
    }
}
//
//#Preview {
//    ProfileView(vm: ViewModel())
//}
