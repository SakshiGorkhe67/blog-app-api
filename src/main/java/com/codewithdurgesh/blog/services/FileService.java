package com.codewithdurgesh.blog.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {

    //Upload Image
    String uploadPostImag(String path, MultipartFile file)throws IOException;

    //Serve Image
    InputStream getResource(String path,String fileName)throws FileNotFoundException;
}

