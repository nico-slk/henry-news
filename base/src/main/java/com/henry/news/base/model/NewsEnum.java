package com.henry.news.base.model;

public enum NewsEnum {

    VIDEO("VIDEO_NEWS"),
    IMAGENES("IMAGES_NEWS"),
    TEXTO("TEXT_NEWS");

    private String description;

    NewsEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static NewsEnum find(String value){
        for( NewsEnum v: values()){
            if(v.toString().equalsIgnoreCase(value)){
                return v;
            }
        }
        throw new IllegalArgumentException(String.format("Invalid NewsType: %s", value));
    }
}
