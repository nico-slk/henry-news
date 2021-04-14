package com.henry.news.base.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "TEXT_NEWS")

public class NewsText extends News {

    private String texto;

    @Override
    public NewsEnum newsEnum() {
        return NewsEnum.TEXTO;
    }
}
