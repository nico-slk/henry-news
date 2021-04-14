package com.henry.news.base.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.AccessType;

import javax.persistence.*;
import java.io.Serializable;


@Data
@NoArgsConstructor
@Entity

@JsonTypeInfo(use= JsonTypeInfo.Id.NAME, visible = true, property = "newsEnum")
@JsonSubTypes({
        @JsonSubTypes.Type(value = NewsVideo.class, name = "VIDEO"),
        @JsonSubTypes.Type(value = NewsImages.class, name = "IMAGENES"),
        @JsonSubTypes.Type(value = NewsText.class, name = "TEXTO"),

})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class News implements Serializable {

    @Id
    private Integer id;
    private String title;
    private String description;

    @AccessType(AccessType.Type.PROPERTY)
    public abstract NewsEnum newsEnum();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "writer_id")
    private Writer owner;

}
