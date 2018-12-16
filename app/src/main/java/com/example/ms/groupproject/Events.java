package com.example.ms.groupproject;

public class Events {

    String Day;
    String EventType;
    String EventName;
    String EventDate;
    String EventTime;
    String EventDescription;
    String EventAddress;

    public Events (String Day, String EventType, String EventName, String EventDate, String EventTime, String EventDescription, String EventAddress) {
        this.Day = Day;
        this.EventAddress = EventAddress;
        this.EventDate = EventDate;
        this.EventDescription = EventDescription;
        this.EventName = EventName;
        this.EventType = EventType;
        this.EventTime = EventTime;
    }

    public Events(){
    }
}