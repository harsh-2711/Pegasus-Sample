package com.example.harsh.homeautomation.Models;

public class Appliance {

    String name;
    Boolean status;
    String clientID;
    String applicationID;
    String supportID;
    String powerConsumed;
    int timeElapsed;

    public Appliance(String name, Boolean status, String clientID, String applicationID, String supportID, String powerConsumed, int timeElapsed) {
        this.name = name;
        this.status = status;
        this.clientID = clientID;
        this.applicationID = applicationID;
        this.supportID = supportID;
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

    public String getApplicationID() {
        return applicationID;
    }

    public String getSupportID() {
        return supportID;
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

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public void setSupportID(String supportID) {
        this.supportID = supportID;
    }

    public void setPowerConsumed(String powerConsumed) {
        this.powerConsumed = powerConsumed;
    }

    public void setTimeElapsed(int timeElapsed) {
        this.timeElapsed = timeElapsed;
    }
}
