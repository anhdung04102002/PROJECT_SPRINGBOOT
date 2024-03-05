package com.example.du_an_thuc_te.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
@Service
public class FileSystemStoreService implements StorageService
{
    private  final Path rootLocation ;
    public FileSystemStoreService() {
        this.rootLocation = Paths.get("src/main/resources/static/uploads");
    }
    @Override
    public void store(MultipartFile file) {
        try {
//            if (file.isEmpty()) {
//                throw new StorageException("Failed to store empty file.");
//            }
            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
//            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
//                // This is a security check
//                throw new StorageException(
//                        "Cannot store file outside current directory.");
//            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
            try {
                Files.createDirectories(rootLocation);
            }catch (Exception e) {
                e.printStackTrace();
            }
    }
}
