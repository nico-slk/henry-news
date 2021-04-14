package com.henry.news.base.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "IMAGES_NEWS")

public class NewsImages extends News {

    @ElementCollection
    private List<String> urlImages;

    @Override
    public NewsEnum newsEnum() {
        return NewsEnum.IMAGENES;
    }
}

