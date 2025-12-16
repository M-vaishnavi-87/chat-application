# Java Swing Chat Application

A simple **client–server chat application** built using **Java Swing** for the GUI and **Socket Programming** for real-time communication.

This project demonstrates core networking concepts and basic desktop UI development in Java.

---

## Features
- Client–Server architecture using TCP sockets
- Real-time text messaging
- Java Swing based user interface
- Uses `ServerSocket` and `Socket`
- Data transfer via `DataInputStream` and `DataOutputStream`

---

## Tech Stack
- Java (JDK 8+)
- Java Swing
- Socket Programming (TCP)

  ## How It Works
- The **Server** listens on a specific port using `ServerSocket`
- The **Client** connects to the server using `Socket`
- Messages are sent and received using UTF streams
- Swing components handle user input and message display
