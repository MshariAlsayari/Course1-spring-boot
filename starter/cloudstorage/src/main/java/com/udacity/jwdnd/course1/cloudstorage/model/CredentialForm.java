package com.udacity.jwdnd.course1.cloudstorage.model;

public class CredentialForm {

    private String url;
    private String userName;
    private String password;
    private String credentialId;


    public CredentialForm(String url, String userName, String password, String credentialId) {
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.credentialId = credentialId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }
}
