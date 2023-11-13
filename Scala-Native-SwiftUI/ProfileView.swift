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
                .padding()
                .buttonStyle(.borderless)
                .foregroundColor(.white)
                .handHover()
            
            
            List {
                Section {
                    ForEach(twots) {
                        TwotView(twot: $0, vm: self.vm)
                    }.listRowBackground(Color.purpleVomit())
                }.purpleVomit()
            }.task {
                switch vm.state {
                case .showProfile(let string):
                    getTwots(profile: string)
                case _:
                    vm.state = .timeline
                }
            }.listStyle(.plain)
            
            Spacer()
        }.purpleVomit()
    }
    
    func goBack() {
        self.vm.state = .timeline
    }
    
    func getTwots(profile: String) {
        self.loading = true
        defer {
            self.loading = false
        }
        
        let twots = Interop.sendRequest(request: .getThoughtLeader(GetThoughtLeader.Request.with {
            $0.nickname = profile
        }))
        if case .Ok(.getThoughtLeader(let tl)) = twots {
            self.twots = tl.thoughtLeader.twots
            lastRefreshed = Date.now
        }
        
    }
}

#Preview {
    ProfileView(vm: ViewModel())
}
