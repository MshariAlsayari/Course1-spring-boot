package com.udacity.jwdnd.course1.cloudstorage;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseTest {

    @LocalServerPort
    public int port;

    public WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    public HomePage doRegistrationAndLogin() {
        driver.get("http://localhost:" + this.port + "/signup");
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.setFirstName("Mshari");
        registrationPage.setLastName("Alsayari");
        registrationPage.setUserName("mshari");
        registrationPage.setPassword("123");
        registrationPage.clickBtnRegister();
        driver.get("http://localhost:" + this.port + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUserName("mshari");
        loginPage.setPassword("123");
        loginPage.clickBtnLogin();
        return new HomePage(driver);
    }
}
