package com.example.harsh.homeautomation.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Floor implements Serializable {

    private int floorID;
    private String floorName;
    private int floorImage;
    private ArrayList<Room> rooms;

    public Floor() {
    }

    public Floor(int floorID, String floorName, int floorImage, ArrayList<Room> rooms) {
        this.floorID = floorID;
        this.floorName = floorName;
        this.floorImage = floorImage;
        this.rooms = rooms;
    }

    public int getFloorID() {
        return floorID;
    }

    public String getFloorName() {
        return floorName;
    }

    public int getFloorImage() {
        return floorImage;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setFloorID(int floorID) {
        this.floorID = floorID;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public void setFloorImage(int floorImage) {
        this.floorImage = floorImage;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }
}
