package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/home")
public class HomeController {

    private final UserService userService;
    private final FileMapper fileMapper;
    private final NoteMapper noteMapper;
    private final CredentialMapper credentialMapper;

    public HomeController(UserService userService, FileMapper fileMapper, NoteMapper noteMapper, CredentialMapper credentialMapper) {
        this.userService = userService;
        this.fileMapper = fileMapper;
        this.noteMapper = noteMapper;
        this.credentialMapper = credentialMapper;
    }


    @GetMapping
    public String getHomePage(
            Authentication authentication, @ModelAttribute("newFile") FileForm newFile,
            @ModelAttribute("newNote") NoteForm newNote,
            @ModelAttribute("newCredential") CredentialForm newCredential,
            Model model) {
        Integer userId = getUserId(authentication);
        model.addAttribute("showFileExample", fileMapper.getFileListings(userId).length == 0);
        model.addAttribute("showNoteExample", noteMapper.getNoteListings(userId).length == 0);
        model.addAttribute("showCredentialExample", credentialMapper.getCredentialListings(userId).length == 0);
        model.addAttribute("files", this.fileMapper.getFileListings(userId));
        model.addAttribute("notes", this.noteMapper.getNoteListings(userId));
        model.addAttribute("credentials", this.credentialMapper.getCredentialListings(userId));
        return "home";
    }

    private Integer getUserId(Authentication authentication) {
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        return user.getUserId();
    }



}
