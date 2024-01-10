package com.example.server;
import org.glassfish.tyrus.server.Server;

public class TyrusServer {
    Server server;

    public TyrusServer() {
        this.server = new Server("localhost", 8025, "yatzy", null, ServerEndpoint.class);
    }

    public void runServer() {
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //server.stop();
        }
    }
}
