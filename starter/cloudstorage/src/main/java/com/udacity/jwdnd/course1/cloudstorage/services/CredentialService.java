package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;
    private final UserMapper userMapper;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, UserMapper userMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.userMapper = userMapper;
        this.encryptionService = encryptionService;
    }

    public void createCredential(CredentialForm form ,String userName) {
        Integer userId = userMapper.getUser(userName).getUserId();
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        Pair<String, String> pair = getEncryptedPasswordAndKey(form.getPassword());
        Credential credential = new Credential(0, form.getUrl(), form.getUserName(), pair.getLeft(),pair.getRight(), userId);
        credentialMapper.insert(credential);
    }

    public Pair<String,String> getEncryptedPasswordAndKey(String password){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String encryptedPassword = encryptionService.encryptValue(password, encodedSalt);

        return new ImmutablePair<String,String>(encodedSalt,encryptedPassword);

    }

    public void updateCredential(Credential credential) {
        credentialMapper.updateCredential(credential.getCredentialid(), credential.getUserName(), credential.getUrl(), credential.getKey() ,credential.getPassword());
    }

    public void deleteCredential(Integer credentialId) {
         credentialMapper.deleteCredential(credentialId);
    }

    public Credential getCredential(Integer credentialId) {
        return credentialMapper.getCredential(credentialId);
    }
}
