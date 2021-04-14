package com.henry.news.base.controller;

import com.henry.news.base.converter.WriterToWriterDTOConverter;
import com.henry.news.base.model.Writer;
import com.henry.news.base.model.dto.WriterDTO;
import com.henry.news.base.service.WriterService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/writer")
public class WriterController {

    private final WriterService writerService;
    private final ConversionService conversionService;

    @Autowired
    public WriterController(WriterService writerService, ConversionService conversionService, WriterToWriterDTOConverter writerToWriterDTOConverter) {
        this.writerService = writerService;
        this.conversionService = conversionService;
    }

    @GetMapping
    @Operation(summary = "Lista de escritores")
    public List<Writer> getWriters(){
        return writerService.getWriters();
    }

    @GetMapping("/writerDTO")
    @Operation(summary = "Lista de escritores DTO (sin dni)")
    public List<WriterDTO> getAllPersonDTO(){
        return conversionService.convert(writerService.getWriters(), List.class);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta escritor por id")
    public Writer getWriter(@PathVariable Integer id) {
        return writerService.getWriter(id);
    }

    @GetMapping("/writerDTO/{id}")
    @Operation(summary = "Consulta escritor DTO por id")
    public WriterDTO getWriterDTOByID(@PathVariable Integer id){
        return conversionService.convert(writerService.getWriter(id), WriterDTO.class);
    }

    @PostMapping
    @Operation(summary = "Dar de alta un escritor")
    public String addWriter(@RequestBody Writer writer) {
        Writer createWriter = writerService.addWriter(writer);
        return ("Persona creada: " + createWriter);
    }

    @PutMapping
    @Operation(summary = "Editar escritor")
    public String editWriter(@RequestBody Writer writer){
        Writer editWriter = writerService.editPerson(writer);
        return ("Escritor editado " + editWriter);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar escritor")
    public String deleteWrite(@PathVariable Integer id){
        writerService.deleteWriteByid(id);
        return ("Escritor con id " + id + " ha sido borrada.");
    }

}
