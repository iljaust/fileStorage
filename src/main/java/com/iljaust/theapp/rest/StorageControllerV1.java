package com.iljaust.theapp.rest;

import com.iljaust.theapp.model.Event;
import com.iljaust.theapp.model.User;
import com.iljaust.theapp.service.StorageService;
import com.iljaust.theapp.service.UserService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
@RequestMapping("/api/v1/file")
public class StorageControllerV1 {

    private StorageService service;
    private UserService userService;


    public StorageControllerV1(StorageService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }


    @GetMapping("/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = service.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        return new ResponseEntity<>(service.deleteFile(fileName), HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") File file) {
        User user = userService.findById((long)1);
        return new ResponseEntity<>(service.uploadFile(user,file), HttpStatus.OK);
    }


}
