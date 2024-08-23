# Chatbox
The chatbox project involves creating a simple chat application in Java with both a server and client, each with a graphical user interface (GUI) built using Swing.
This Java chat application project is a simple yet effective demonstration of client-server communication using Java's networking API. The project allows multiple clients to connect to a central server and exchange messages in real-time. Below is a breakdown of the key components and functionalities:

1. PROJECT COMPONENTS

  a. Chat Server
Role: The server acts as a mediator between clients, facilitating the exchange of messages.
Functionality:
Listens for incoming client connections on a specific port (e.g., 1234).
Handles multiple clients simultaneously using threading.
Broadcasts messages received from one client to all other connected clients.
Maintains a list of connected clients to manage connections and relay messages.



    b. Chat ClientRole: The client is a participant in the chat, sending and receiving messages via the server.
Functionality:
Connects to the server using its hostname and port.
Allows users to enter their names and chat messages through the console.
Displays messages from other users received from the server.
Supports multiple instances, allowing several clients to join the chat room.

 2. KEYS CONCEPT

  
    a. Sockets and Networking

    Socket: A socket is an endpoint for sending or receiving data across a network. The server uses a ServerSocket to listen for connections, and each client uses 
    a Socket to connect to the server.
       Networking: This project leverages Java's networking capabilities to establish communication channels between the server and clients.

    
b. Multithreading
Server Multithreading: The server uses multiple threads to handle multiple client connections simultaneously. Each client is managed by a separate ClientHandler thread, allowing the server to process messages from different clients concurrently.

Client Multithreading: The client also uses separate threads for reading messages from the server and sending messages, enabling real-time interaction without blocking the main application flow.


c. Input/Output Streams
InputStream/OutputStream: These are used to send and receive data between the server and clients. The server reads client messages through InputStream and sends responses using OutputStream.
BufferedReader/PrintWriter: These classes are used to simplify reading from and writing to sockets, allowing for easier handling of text data.
