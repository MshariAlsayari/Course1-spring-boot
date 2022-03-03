package com.udacity.jwdnd.course1.cloudstorage;


import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialTest  extends CloudStorageApplicationTests {


    @Test
    public void teat_create_credential() {
        String credentialUrl = "ftp://foo.bar.com/";
        String credentialUsername = "this is my first credential.";
        HomePage homePage = doRegistrationAndLogin();
        initCredential(credentialUrl, credentialUsername, homePage);
        homePage = new HomePage(driver);
        Credential credential = homePage.getCredential();
        Assertions.assertEquals(credentialUrl, credential.getUrl());
        Assertions.assertEquals(credentialUsername, credential.getUserName());
    }

    @Test
    public void teat_delete_credential() {
        String credentialUrl = "ftp://foo.bar.com/";
        String credentialUsername = "this is my first credential.";
        HomePage homePage = doRegistrationAndLogin();
        initCredential(credentialUrl, credentialUsername, homePage);
        homePage = new HomePage(driver);
        deleteCredential(homePage);
        Credential credential = homePage.getExampleCredential();
        Assertions.assertEquals("Example Credential URL", credential.getUrl());
        Assertions.assertEquals("Example Credential Username", credential.getUserName());
    }

    @Test
    public void teat_edit_credential() {
        String credentialUrl = "ftp://foo.bar.com/";
        String credentialUsername = "this is my first credential.";
        String updatedCredentialUrl = "http://foo.bar.com/";
        String updatedCredentialUsername = "my first updated credential.";
        HomePage homePage = doRegistrationAndLogin();
        initCredential(credentialUrl, credentialUsername, homePage);
        homePage = new HomePage(driver);
        editCredential(updatedCredentialUrl, updatedCredentialUsername,homePage);
        Credential credential = homePage.getCredential();
        Assertions.assertEquals(updatedCredentialUrl, credential.getUrl());
        Assertions.assertEquals(updatedCredentialUsername, credential.getUserName());
        deleteCredential(homePage);
    }










    private void initCredential(String url, String username, HomePage page){
        page.navTo(HomePage.Tabs.CREDENTIALS);
        page.clickOnAddCredentialButton();
        page.setCredentialUrl(url);
        page.setCredentialUsername(username);
        page.setCredentialPassword("123");
        page.clickOnSubmitCredentialButton();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickBtnSuccessResult();
        page.navTo(HomePage.Tabs.CREDENTIALS);
    }

    private void deleteCredential(HomePage page){
        page.navTo(HomePage.Tabs.CREDENTIALS);
        page.clickOnDeleteCredentialButton();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickBtnSuccessResult();
        page.navTo(HomePage.Tabs.CREDENTIALS);
    }

    private void editCredential(String url, String username, HomePage page){
        page.navTo(HomePage.Tabs.CREDENTIALS);
        page.clickOnEditCredentialButton();
        page.setCredentialUrl(url);
        page.setCredentialUsername(username);
        page.setCredentialPassword("123");
        page.clickOnSubmitCredentialButton();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickBtnSuccessResult();
        page.navTo(HomePage.Tabs.CREDENTIALS);
    }
}
