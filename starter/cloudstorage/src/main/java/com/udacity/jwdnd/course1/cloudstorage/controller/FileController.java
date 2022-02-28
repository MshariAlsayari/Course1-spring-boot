package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller()
@RequestMapping("file")
public class FileController {


    private final UserService userService;
    private final FileService fileService;

    public FileController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }


    @PostMapping
    public String submitFile(Authentication authentication, @ModelAttribute("newFile") FileForm newFile, Model model) throws IOException {
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        Integer userId = user.getUserId();
        MultipartFile multipartFile = newFile.getFile();
        String fileName = multipartFile.getOriginalFilename();
        boolean fileIsDuplicate = fileService.isFileDuplicated(userId, fileName);
        boolean isValidFileType = fileService.isValidFileType(multipartFile);

        if (isValidFileType) {
            if (!fileIsDuplicate) {
                fileService.createFile(multipartFile, userName);
                model.addAttribute("result", "success");
            } else {
                model.addAttribute("result", "error");
                model.addAttribute("message", "You have tried to add a duplicate file.");
            }
        }else {
            model.addAttribute("result", "error");
            model.addAttribute("message", "The accepted file just PDF.");
        }


        return "result";
    }

    @GetMapping(value ="/deleteFile/{fileName}")
    public String deleteFile(@PathVariable String fileName, Model model) {
        fileService.deleteFile(fileName);
        model.addAttribute("result", "success");
        return "result";
    }

    @GetMapping(value ="/getFile/{fileName}", produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody byte[] getFile(@PathVariable String fileName) {
        return fileService.getFile(fileName).getFileData();
    }
}
