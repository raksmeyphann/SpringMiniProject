package com.example.bookmanagement.services.impl;

import com.example.bookmanagement.services.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {

    @Override
    public String singleFileUpload(MultipartFile file, String folder) {

        if (file.isEmpty())
            return null;


        if (folder == null)
            folder = "";

        File path = new File("/pp6th/" + folder);

        if (!path.exists())
            path.mkdir();


        String filename = file.getOriginalFilename();

        String extension = filename.substring(filename.lastIndexOf('.') + 1);

        System.out.println(filename);
        System.out.println(extension);


        filename = UUID.randomUUID() + "." + extension;


        System.out.println(filename);

        try {
            Files.copy(file.getInputStream(), Paths.get("/pp6th/" + folder, filename));
        } catch (IOException e) {

        }

        return folder + filename;
    }


    @Override
    public List<String> multipleFileUpload(List<MultipartFile> files, String folder) {
        List<String> filenames = new ArrayList<>();

        files.forEach(file -> {
            filenames.add(this.singleFileUpload(file, folder));
        });

       /* for (MultipartFile file :
                files) {
            filenames.add(this.singleFileUpload(file, folder));
        }*/

        return filenames;
    }

    @Override
    public String upload(MultipartFile file, String folder) {
        return this.singleFileUpload(file, folder);
    }

    @Override
    public String upload(MultipartFile file) {
        return this.singleFileUpload(file, null);
    }

    @Override
    public List<String> upload(List<MultipartFile> files, String folder) {
        return this.multipleFileUpload(files, folder);
    }

    @Override
    public List<String> upload(List<MultipartFile> files) {
        return this.multipleFileUpload(files, null);
    }
}
