# 📱 ChatApp
ChatApp is a modern chat application built for Android, featuring real-time messaging powered by a custom backend using Socket.IO. The app leverages Retrofit for traditional HTTP network operations and embraces Dagger Hilt for dependency injection, all while using Jetpack Compose to deliver a modern UI experience. The project follows MVI architecture and adheres to clean architecture principles with a clear separation into data, domain, and presentation layers.

## 🏗️ Architecture Overview

```ruby
:data  <-  :domain  ->  :presentation
```

- *Domain Layer*: Contains business logic and use cases, written in pure Kotlin. This layer is independent of any Android platform code.
- *Data Layer*: Provides implementations of repository interfaces defined in the domain layer. This is the only layer that directly interacts with external systems (network, database, etc.).
- *Presentation Layer*: Contains UI (built using Jetpack Compose) and user interaction handling. It is only dependent on the domain layer, and retrieves actual implementations from the data layer at runtime via Dagger Hilt.


## 🔧 Tech Stack
Technology	Purpose
Kotlin	Primary language, ensuring modern, concise code.
Jetpack Compose	Declarative UI framework for building the app’s user interface.
Dagger Hilt	Dependency Injection framework for seamless dependency management across layers.
Retrofit	HTTP client for network operations such as login, create room, edit room, etc.
Socket.IO	Real-time socket-based communication for chat messages, room entry/exit events, and more.
Coroutines + Flow	For async operations and reactive data streams.


## ⚙️ Features
- ✅ User Login and Authentication (via HTTP/Retrofit)
- ✅ Create Chat Room, Edit Room Details (via HTTP/Retrofit)
- ✅ Real-time Messaging (via Socket.IO)
- ✅ Join and Leave Rooms (via Socket.IO)
- ✅ Clear Separation of Concerns (data, domain, presentation)
- ✅ Clean and Modern UI with Jetpack Compose
- ✅ Fully Written in Kotlin

## 📂 Project Structure

```bash
/data          - Platform-dependent implementations (network, socket, repository impls)
/domain        - Business logic, Use Cases, Repository Interfaces (pure Kotlin)
/presentation  - UI, ViewModels, User interactions (Jetpack Compose + MVI)
```

## 🔗 Network Flow

HTTP (Retrofit)
- Login
- Create Room
- Edit Room Details

Realtime (Socket.IO)
- Send/Receive Chat Messages
- Join/Leave Room Events

## 🔌 Dependency Injection
- Dagger Hilt wires up all dependencies across layers.
- presentation layer asks for repository interfaces, and Hilt provides the data layer’s implementations.
- This keeps the presentation layer decoupled and testable.

## 🚀 How It Works
- User logs in (handled via Retrofit call).
- User creates or joins a chat room (via Retrofit for room creation/edit and Socket.IO for real-time presence management).
- User sends and receives messages in real-time (via Socket.IO).
- Room exit and entry are handled via socket events.
- UI updates reactively based on flows emitted from the domain layer.


<img src="https://github.com/user-attachments/assets/1b8c0c47-84f0-49f0-9c70-07e7dcd58093" width="300px"/>
