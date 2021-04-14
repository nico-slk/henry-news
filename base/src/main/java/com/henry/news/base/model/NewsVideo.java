package com.henry.news.base.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "VIDEO_NEWS")

public class NewsVideo extends News {

    private String videoTitle;
    private String videoDescription;
    private String videoURL;

    @Override
    public NewsEnum newsEnum() {
        return NewsEnum.VIDEO;
    }
}
