package com.example.myapplication;

public class CalendarRecord {

    private int id;
    private String calendar_type;
    private String event_type;
    private String event_name;
    private String event_start_date;
    private String event_end_date;
    private String event_repeat;
    private String event_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCalendar_type() {
        return calendar_type;
    }

    public void setCalendar_type(String calendar_type) {
        this.calendar_type = calendar_type;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_start_date() {
        return event_start_date;
    }

    public void setEvent_start_date(String event_start_date) {
        this.event_start_date = event_start_date;
    }

    public String getEvent_end_date() {
        return event_end_date;
    }

    public void setEvent_end_date(String event_end_date) {
        this.event_end_date = event_end_date;
    }

    public String getEvent_repeat() {
        return event_repeat;
    }

    public void setEvent_repeat(String event_repeat) {
        this.event_repeat = event_repeat;
    }

    public String getEvent_time() {
        return event_time;
    }

    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }

    @Override
    public String toString() {
        return  event_type + " : " +
                event_name ;
    }
}
