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
        return formatNumber(startHours) + ":" + formatNumber(startMinutes) + " - " + formatNumber(endHours) + ":" + formatNumber(endMinutes);
    }

    public String formatNumber(int number) {
        String n = Integer.toString(number);
        if (n.length() == 1) {
            return "0" + n;
        }
        return n;
    }
}
