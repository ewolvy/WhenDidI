package com.mooo.ewolvy.whendidi.model;


public class TaskItem {
    private String id;
    private String name;
    private String datesHistory;
    private int color;
    private String lastTime;
    private String remindOn;

    public TaskItem(String id, String name, int color, String datesHistory, String lastTime, String remindOn) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.lastTime = lastTime;
        this.datesHistory = datesHistory;
        this.remindOn = remindOn;
    }

    // Public getters and setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatesHistory() {
        return datesHistory;
    }

    public void setDatesHistory(String datesHistory) {
        this.datesHistory = datesHistory;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getLastDate() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        addToHistory(this.lastTime);
        this.lastTime = lastTime;
    }

    public String getRemindOn() {
        return remindOn;
    }

    public void setRemindOn(String remindOn) {
        this.remindOn = remindOn;
    }

    // Private helper methods
    private void addToHistory (String addTime){
        String updatedHistory = datesHistory +
                "\n" +
                addTime;
        setDatesHistory(updatedHistory);
    }
}
