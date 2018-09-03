package com.example.harsh.homeautomation.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {

    private int roomID;
    private String roomName;
    private int roomImage;
    private ArrayList<String> appliances;

    public Room() {
    }

    public Room(String roomName, int roomID, int roomImage, ArrayList<String> appliances) {
        this.roomName = roomName;
        this.roomID = roomID;
        this.roomImage = roomImage;
        this.appliances = appliances;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getRoomID() {
        return this.roomID;
    }

    public int getRoomImage() {
        return roomImage;
    }

    public ArrayList<String> getAppliances() {
        return appliances;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public void setRoomImage(int roomImage) {
        this.roomImage = roomImage;
    }

}
