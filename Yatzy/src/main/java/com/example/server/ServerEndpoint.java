package com.example.server;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;

@jakarta.websocket.server.ServerEndpoint(value = "/username")
public class ServerEndpoint {
    @OnOpen
    public void connecting(Session session) {
        System.out.println("User connected!");
    }

    @OnClose
    public void close() {}

}
