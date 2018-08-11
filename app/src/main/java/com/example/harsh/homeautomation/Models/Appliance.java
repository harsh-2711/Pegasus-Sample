package com.example.harsh.homeautomation.Models;

public class Appliance {
    String name;
    Boolean status;
    String clientID;
    String powerConsumed;
    int timeElapsed;

    public Appliance(String name, Boolean status, String clientID, String powerConsumed, int timeElapsed) {
        this.name = name;
        this.status = status;
        this.clientID = clientID;
        this.powerConsumed = powerConsumed;
        this.timeElapsed = timeElapsed;
    }

    public String getName() {
        return name;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getClientID() {
        return clientID;
    }

    public String getPowerConsumed() {
        return powerConsumed;
    }

    public int getTimeElapsed() {
        return timeElapsed;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public void setPowerConsumed(String powerConsumed) {
        this.powerConsumed = powerConsumed;
    }

    public void setTimeElapsed(int timeElapsed) {
        this.timeElapsed = timeElapsed;
    }
}
