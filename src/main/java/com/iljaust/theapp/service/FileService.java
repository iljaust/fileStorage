package com.iljaust.theapp.service;

import com.iljaust.theapp.dto.FileDto;
import com.iljaust.theapp.model.File;
import com.iljaust.theapp.model.User;
import com.iljaust.theapp.repository.FileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File save(FileDto fileDto) {
        File file = toFile(fileDto);
        fileRepository.save(file);
        return file;
    }

    public List<File> getAll() {

        List<File> result = fileRepository.findAll();

        return result;
    }

    public File findByName(String filename) {
        File result = fileRepository.findByName(filename);
        return result;
    }

    public File findById(Long id) {
        File result = fileRepository.findById(id).orElse(null);

        return result;
    }

    public void delete(Long id) {
        fileRepository.deleteById(id);

    }

    private File toFile(FileDto fileDto) {
        File file = new File();
        file.setId(fileDto.getId());
        file.setName(fileDto.getName());
        file.setStatus(fileDto.getStatus());
        file.setUser(fileDto.getUser());
        return file;
    }
    public FileDto fromFile(com.iljaust.theapp.model.File file) {
        FileDto fileDto = new FileDto();
        fileDto.setName(file.getName());
        fileDto.setStatus(file.getStatus());
        fileDto.setUser(file.getUser());
        fileDto.setId(file.getId());

        return fileDto;
    }
}
