//
//  UserHeaderView.swift
//  Scala-Native-SwiftUI
//
//  Created by Anton Sviridov on 08/11/2023.
//

import SwiftUI

struct UserHeaderView: View {
    private let vm: ViewModel;
    init(vm: ViewModel) {
        self.vm = vm
    }

    var body: some View {
        HStack {
            LogoView()
            Spacer()
            VStack {
                if let nickname = vm.getNickname() {
                    Text("@\(nickname)").bold()
                }
                Button(action: logOut) {
                    Text("Log out").foregroundColor(.red).bold()
                }
            }
        }.padding().purpleVomit()

    }
    
    func logOut() {
        vm.logOut()
    }

}
//
//#Preview {
//    UserHeaderView(vm: ViewModel())
//}
