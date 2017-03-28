package com.example.arnal.popularmovieapp2.model;

/**
 * Created by arnal on 3/28/17.
 */

public class MessageEvent {
    public final String fragmentType;
    public final int position;
    public final int id;

    public MessageEvent(int position, String fragmentType, int id) {
        this.position = position;
        this.fragmentType = fragmentType;
        this.id = id;
    }
}
