# Scala Native ❤️ SwiftUI ❤️ Protobuf

This repository hosts the code for both the 

1. [Blog post about building a SwiftUI application that interfaces with some Scala Native code](2023-11-24-scala-native-from-swift)
2. and the toy implementation of a MacOS desktop app for Twotm8.com

At the moment it's pretty annoying to build it yourself, most steps are automated using Mill, 
but one isn't - you need to make sure that 

1. SwiftProtobuf binary plugin for protoc  
2. protoc itself
3. ScalaPB's binary plugin for protoc

are all installed.

Then you can run `./mill -i buildMacos` and it will still probably fail because of anything 
ranging from weather to Xcode version problems.
