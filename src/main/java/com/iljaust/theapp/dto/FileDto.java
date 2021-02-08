package com.iljaust.theapp.dto;

import com.iljaust.theapp.model.FileStatus;
import com.iljaust.theapp.model.User;
import lombok.Data;

@Data
public class FileDto {
    private Long id;
    private String name;
    private FileStatus status;
    private User user;
}
