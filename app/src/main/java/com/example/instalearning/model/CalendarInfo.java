package com.example.instalearning.model;

public class CalendarInfo {
    String date;
    String eventName;
    String eventDescription;

    public CalendarInfo(String date, String eventName, String eventDescription) {
        this.date = date;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
}
