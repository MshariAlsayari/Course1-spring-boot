package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UploadedFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import static com.udacity.jwdnd.course1.cloudstorage.Constants.ACCEPTED_FILES_TYPES;

@Service
public class FileService {

    private final FileMapper fileMapper;
    private final UserMapper userMapper;

    public FileService(FileMapper fileMapper, UserMapper userMapper) {
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
    }

    public Boolean isFileDuplicated(int userId , String fileName){
        String[] files = fileMapper.getFileListings(userId);
        String isDuplicated = Arrays.stream(files).filter(f-> f.equals(fileName))
                .findFirst()
                .orElse(null);
        return isDuplicated != null;
    }

    public Boolean isValidFileType(MultipartFile multipartFile){
        String isValid = Arrays.stream(ACCEPTED_FILES_TYPES).filter(ft-> ft.equals(multipartFile.getContentType()))
                .findFirst()
                .orElse(null);
        return isValid != null;
    }



    public void createFile(MultipartFile multipartFile, String userName) throws IOException {
        InputStream fis = multipartFile.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = fis.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        byte[] fileData = buffer.toByteArray();

        String fileName = multipartFile.getOriginalFilename();
        String contentType = multipartFile.getContentType();
        String fileSize = String.valueOf(multipartFile.getSize());
        Integer userId = userMapper.getUser(userName).getUserId();

        UploadedFile file = new UploadedFile(0, fileName, contentType, fileSize, userId, fileData);
        fileMapper.insert(file);
    }

    public void deleteFile(String fileName){
        fileMapper.deleteFile(fileName);
    }

    public UploadedFile getFile(String fileName) {
        return fileMapper.getFile(fileName);
    }

}
