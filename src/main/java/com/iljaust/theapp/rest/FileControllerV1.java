package com.iljaust.theapp.rest;

import com.iljaust.theapp.dto.FileDto;
import com.iljaust.theapp.model.File;
import com.iljaust.theapp.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
public class FileControllerV1 {
    private FileService fileService;

    public FileControllerV1(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<FileDto> getDeveloper(@PathVariable("id") Long fileID) {
        File file  = fileService.findById(fileID);

        if (file == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        FileDto fileDto = fileService.fromFile(file);

        return new ResponseEntity<>(fileDto, HttpStatus.OK);
    }



    @GetMapping
    public ResponseEntity<List<File>> getAllDevelopers() {
        List<File> files = this.fileService.getAll();

        if (files.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(files, HttpStatus.OK);
    }
}
