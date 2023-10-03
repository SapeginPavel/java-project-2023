package com.sapegin.structures;

public class OpeningHours {
    int startHours;
    int startMinutes;
    int endHours;
    int endMinutes;

    public OpeningHours(int startHours, int startMinutes, int endHours, int endMinutes) {
        this.startHours = startHours;
        this.startMinutes = startMinutes;
        this.endHours = endHours;
        this.endMinutes = endMinutes;
    }

    @Override
    public String toString() {
        return startHours + ":" + startMinutes + " - " + endHours + ":" + endMinutes;
    }
}
