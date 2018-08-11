package com.example.harsh.homeautomation.Models;

public class Room {

    private String roomName;
    private int roomImage;

    public Room() {}

    public Room(String roomName, int roomImage) {
        this.roomName = roomName;
        this.roomImage = roomImage;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getRoomImage() {
        return roomImage;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setRoomImage(int roomImage) {
        this.roomImage = roomImage;
    }

}
