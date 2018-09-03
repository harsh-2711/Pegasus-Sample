package com.example.harsh.homeautomation.Models;

import java.io.Serializable;

public class Room implements Serializable {

    private String roomName;
    private int roomImage;
    private String[] appliances;

    public Room() {
    }

    public Room(String roomName, int roomImage, String[] appliances) {
        this.roomName = roomName;
        this.roomImage = roomImage;
        this.appliances = appliances;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getRoomImage() {
        return roomImage;
    }

    public String[] getAppliances() {
        return appliances;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setRoomImage(int roomImage) {
        this.roomImage = roomImage;
    }

}
