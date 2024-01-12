package com.example.client;

import com.example.TestJson;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private String url;
    private int port;
    private Socket socket;
//    private DataInputStream clientDataInput;
//    private DataOutputStream clientDataOutput;
    private ObjectInputStream clientObjectInput;
    private ObjectOutputStream clientObjectOutput;

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 8000);
        System.out.println("Client initialized");
        client.run();
    }

    public Client(String url, int port) {
        this.url = url;
        this.port = port;
        try {
            socket = new Socket(url, port);
//            clientDataInput = new DataInputStream(socket.getInputStream());
//            clientDataOutput = new DataOutputStream(socket.getOutputStream());
            clientObjectOutput = new ObjectOutputStream(socket.getOutputStream());
            clientObjectInput = new ObjectInputStream(socket.getInputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
//            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
//            throw new RuntimeException(e);
        }
    }

    public void run() {
        TestJson input;
        try {
            while (true) {
                input = (TestJson) clientObjectInput.readObject();
                System.out.println(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}