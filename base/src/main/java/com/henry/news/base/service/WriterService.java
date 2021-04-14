package com.henry.news.base.service;

import com.henry.news.base.repository.WriterRepository;
import com.henry.news.base.model.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class WriterService {

    @Autowired
    private WriterRepository writerRepository;

    public List<Writer> getWriters() {
        return writerRepository.findAll();
    }

    public Writer addWriter(Writer writer) {
        return writerRepository.save(writer);
    }

    public Writer getWriter(Integer id) {
        return writerRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public void deleteWriteByid(Integer id) {
        writerRepository.deleteById(id);
    }

    public Writer editPerson(Writer write) {
        Writer writer = writerRepository.findById(write.getId())
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        Writer editWriter = new Writer();
        editWriter.setId(writer.getId());

        if (write.getName() != null) {
            editWriter.setName(write.getName());
        } else {
            editWriter.setName(writer.getName());
        }

        if (write.getLastName() != null) {
            editWriter.setLastName(write.getLastName());
        } else {
            editWriter.setLastName(writer.getLastName());
        }

        if (write.getDni() != null) {
            editWriter.setDni(write.getDni());
        } else {
            editWriter.setDni(writer.getDni());
        }

        return writerRepository.save(editWriter);
    }
}
