package com.example.network;

import com.example.game.GameMove;

import java.io.Serializable;

public class Packet implements Serializable {
    private Header header;
    private Object payload;

    public Packet(Header header, Object payload) {
        this.header = header;
        this.payload = payload;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
