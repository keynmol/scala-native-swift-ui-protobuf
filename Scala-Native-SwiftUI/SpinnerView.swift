//
//  SpinnerView.swift
//  Scala-Native-SwiftUI
//
//  Created by Anton Sviridov on 12/11/2023.
//

import SwiftUI

struct SpinnerView: View {
    private var loading: Binding<Bool>;
    
    init(loading: Binding<Bool>) {
        self.loading = loading
    }
    
    var body: some View {
        Text("loading...").italic().opacity(loading.wrappedValue ? 1: 0)
    }
}

#Preview {
    SpinnerView(loading: Binding.constant(true))
}
