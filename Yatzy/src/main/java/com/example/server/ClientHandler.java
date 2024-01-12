package com.example.server;

import com.example.TestJson;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class ClientHandler implements Runnable {
    //        private DataInputStream clientDataInput;
//        private DataOutputStream clientDataOutput;
    private ObjectInputStream clientObjectInput;
    private ObjectOutputStream clientObjectOutput;
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
//                clientDataInput = new DataInputStream(socket.getInputStream());
//                clientDataOutput = new DataOutputStream(socket.getOutputStream());
            clientObjectOutput = new ObjectOutputStream(socket.getOutputStream());
            clientObjectInput = new ObjectInputStream(socket.getInputStream());
//                clientDataOutput.writeUTF("Tere Maailm!");
            TestJson json = new TestJson();
            json.setName("Felix");
            json.setAge(36);
            System.out.println("Sending object");
            clientObjectOutput.writeObject(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
