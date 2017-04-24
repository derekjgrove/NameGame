package com.willowtree.namegame.model;

/**
 * Created by Derek on 4/20/2017.
 */

public class Headshot {

    private String type;
    private String mimeType;
    private String id;
    private String url;
    private String alt;
    private int height;
    private int width;

    public Headshot(String type, String mimeType, String id, String url, String alt, int height, int width) {
        this.type = type;
        this.mimeType = mimeType;
        this.id = id;
        this.url = url;
        this.alt = alt;
        this.height = height;
        this.width = width;
    }

    public String getType() {
        return type;
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getAlt() {
        return alt;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
