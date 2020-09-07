package com.certified.gadsproject.retrofit;


public class Hours {

    private String name;

    private int hours;

    private String country;

    private String badgeUrl;

    public Hours(String name, String country, int hours, String badgeUrl) {
        this.name = name;
        this.country = country;
        this.hours = hours;
        this.badgeUrl = badgeUrl;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getHours() {
        return hours;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }
}