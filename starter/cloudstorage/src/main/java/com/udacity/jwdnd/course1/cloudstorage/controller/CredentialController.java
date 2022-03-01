package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller()
@RequestMapping("credential")
public class CredentialController {

    private final UserService userService;
    private final CredentialService credentialService;

    public CredentialController(UserService userService, CredentialService credentialService) {
        this.userService = userService;
        this.credentialService = credentialService;
    }

    @PostMapping
    public String submitCredential(Authentication authentication, @ModelAttribute("newCredential") CredentialForm newCredential, Model model) {
        String userName = authentication.getName();

        if (newCredential.getCredentialId().isEmpty()){
            credentialService.createCredential(newCredential, userName);
        }else {
            Credential credential = getCredential(Integer.parseInt(newCredential.getCredentialId()));
            credential.setUrl(newCredential.getUrl());
            credential.setUserName(newCredential.getUserName());
            if (!credential.getPassword().equals(newCredential.getPassword())){
                Pair<String, String> pair = credentialService.getEncryptedPasswordAndKey(newCredential.getPassword());
                credential.setKey(pair.getLeft());
                credential.setPassword(pair.getRight());

            }
            credentialService.updateCredential(credential);
        }




        model.addAttribute("result", "success");
        return "result";
    }

    @GetMapping(value ="/deleteCredential/{credentialId}")
    public String deleteNote(@PathVariable Integer credentialId, Model model) {
        credentialService.deleteCredential(credentialId);
        model.addAttribute("result", "success");
        return "result";
    }

    public Credential getCredential(Integer credentialId) {
        return credentialService.getCredential(credentialId);
    }


}
