package com.example.du_an_thuc_te;

import com.example.du_an_thuc_te.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Service
@RequestMapping("/upload-test")
public class testUploadFile {
    @Autowired
    private StorageService storageService;
    @GetMapping
    public String demo() {
        return "test-upload";
    }
    @PostMapping()
    public String save(@RequestParam("file") MultipartFile file) {
        this.storageService.store(file);
        return "test-upload";
    }
}
