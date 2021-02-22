package com.iljaust.theapp.dto;

import com.iljaust.theapp.model.FileStatus;
import com.iljaust.theapp.model.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class FileDto {
    private Long id;
    @NotBlank(message = "Username is mandatory")
    @Size(min = 1, max = 40)
    private String name;
    private FileStatus status;
    private User user;
}
