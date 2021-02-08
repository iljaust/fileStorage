package com.iljaust.theapp.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.iljaust.theapp.dto.FileDto;
import com.iljaust.theapp.model.Event;
import com.iljaust.theapp.model.EventAction;
import com.iljaust.theapp.model.FileStatus;
import com.iljaust.theapp.model.User;
import com.iljaust.theapp.repository.EventRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;

@Service
public class StorageService {
    @Value("${application.bucket.name}")
    private String bucketName;

    private AmazonS3 s3Client;

    private FileService fileService;
    private EventRepository eventRepository;


    public StorageService(AmazonS3 s3Client, FileService fileService, EventRepository eventRepository) {
        this.s3Client = s3Client;
        this.fileService = fileService;
        this.eventRepository = eventRepository;
    }

    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();

        FileDto fileDto =  fromFile(fileService.findByName(fileName));
        Event event = fromFileDto(fileDto, EventAction.DOWNLOAD);
        eventRepository.save(event);

        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        FileDto fileDto =  fromFile(fileService.findByName(fileName));
        fileDto.setStatus(FileStatus.DELETED);
        fileService.save(fileDto);
        Event event = fromFileDto(fileDto, EventAction.DELETE);
        eventRepository.save(event);

        return fileName + " removed ...";
    }

    public String uploadFile(User user,File file) {

        s3Client.putObject(bucketName, file.getName(), file);
        FileDto fileDto = fromFileIO(user,file,FileStatus.ACTIVE);
        fileService.save(fileDto);
        Event event = fromFileDto(fileDto, EventAction.UPLOAD);
        eventRepository.save(event);
        return "Deleted file: " + file.getName();
    }


    private FileDto fromFileIO(User user,File file,FileStatus status) {
        FileDto fileDto = new FileDto();
        fileDto.setName(file.getName());
        fileDto.setStatus(status);
        fileDto.setUser(user);

        return fileDto;
    }

    private FileDto fromFile(com.iljaust.theapp.model.File file) {
        FileDto fileDto = new FileDto();
        fileDto.setName(file.getName());
        fileDto.setStatus(file.getStatus());
        fileDto.setUser(file.getUser());
        fileDto.setId(file.getId());

        return fileDto;
    }

    private Event fromFileDto(FileDto fileDto,EventAction eventAction){
        Event event = new Event();
        event.setAction(eventAction);
        event.setId(fileDto.getId());
        event.setUser(fileDto.getUser());
        event.setEventTime(LocalDateTime.now());
        return event;

    }






}
