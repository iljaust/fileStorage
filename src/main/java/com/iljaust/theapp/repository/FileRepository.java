package com.iljaust.theapp.repository;

import com.iljaust.theapp.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File,Long> {
    File findByName(String name);
}
