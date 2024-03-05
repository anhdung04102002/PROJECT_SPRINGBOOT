package com.example.du_an_thuc_te.Service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService
{
    void store(MultipartFile file);
    void init();
}
