package com.henry.news.base.controller;

import com.henry.news.base.model.News;
import com.henry.news.base.model.PaginationResponse;
import com.henry.news.base.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @PostMapping
    @Operation(summary = "Agregar noticia")
    public String addNoticia(@RequestBody News news) {
        News postNews = newsService.addNoticia(news);
        return ("Noticia creada: " + postNews);
    }

    @PutMapping("/{id}/writer/{writerID}")
    @Operation(summary = "Agregar escritor a la noticia")
    private String addWriter(@PathVariable Integer id, @PathVariable Integer writerID) {
        newsService.addWriter(id, writerID);
        return ("Agregado escritor con id: " + writerID);
    }

    @GetMapping
    @Operation(summary = "Lista de Noticias paginada")
    public PaginationResponse<News> getAll(@RequestParam(value = "size", defaultValue = "20") Integer size,
                                           @RequestParam(value = "page", defaultValue = "0") Integer page) {
        return newsService.getAll(page, size);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar noticia")
    public String deleteNew(@PathVariable Integer id){
        newsService.deleteWriteByid(id);
        return ("Noticia con el id: " + id + " borrada.");
    }


}