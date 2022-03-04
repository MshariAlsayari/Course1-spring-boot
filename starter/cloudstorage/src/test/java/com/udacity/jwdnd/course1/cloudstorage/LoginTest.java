package com.udacity.jwdnd.course1.cloudstorage;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginTest extends BaseTest {


    @Test
    public void teat_not_registered_user_access_home_page() {
        driver.get("http://localhost:" + this.port + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUserName("mshari");
        loginPage.setPassword("123");
        loginPage.clickBtnLogin();
        Assertions.assertEquals("http://localhost:" + this.port + "/login?error", driver.getCurrentUrl());

    }

    @Test
    public void teat_register_login_logout() {
        HomePage homePage = doRegistrationAndLogin();
        homePage.clickOnLogoutButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickBtnLogin();
        Assertions.assertEquals("http://localhost:" + this.port + "/login?error", driver.getCurrentUrl());
        Assertions.assertEquals("Login", driver.getTitle());
    }
}
