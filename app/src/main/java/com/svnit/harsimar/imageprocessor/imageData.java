package com.svnit.harsimar.imageprocessor;


public class imageData {
    private String imageLink, label, latitude, longitude;

    public imageData(){
        
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public imageData(String imageLink, String label, String latitude, String longitude) {
        this.imageLink = imageLink;
        this.label = label;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}