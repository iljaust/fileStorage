package com.iljaust.theapp.util;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class ApiError {
    private int status;
    private String massage;

}
