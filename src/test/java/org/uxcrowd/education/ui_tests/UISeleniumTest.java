package org.uxcrowd.education.ui_tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.uxcrowd.education.config.ApplicationConfig;
import org.uxcrowd.education.ui_tests.page.ClientTestsPage;
import org.uxcrowd.education.ui_tests.page.LandingPage;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.uxcrowd.education.ui_tests.page.LandingPage.UX_TESTING_BUTTON;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UISeleniumTest {
    private ChromeDriver driver;

    private WebDriverWait wait;

    private ApplicationConfig config;

    @BeforeAll
    public void configInit() {
        config = new ApplicationConfig();
    }

    @BeforeEach
    public void init() throws MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.MINUTES);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.MINUTES);
        wait = new WebDriverWait(driver, Duration.ofSeconds(240));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Step
    public void login(String username, String password){
        driver.get(config.baseUrl);
        LandingPage landingPage = new LandingPage(driver, wait);

        landingPage.clickHeaderLoginButton();
        landingPage.fillLoginInput(username);
        landingPage.fillPasswordInput(password);
        landingPage.submitForm();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/logins.csv", numLinesToSkip = 1)
    public void loginTest(String typeOfClient, String username, String password){

        login(username, password);

        if(typeOfClient.equals("client")){
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid=\"Header profile settings\"]  ")));
            Assertions.assertEquals(driver.getCurrentUrl(), config.clientPageURL);
        } else if (typeOfClient.equals("tester")) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href=\"/app-tester-home/tester-profile\"]")));
            Assertions.assertEquals(driver.getCurrentUrl(), config.testerPageURL);
        }
        else Assertions.fail();
    }

    @Test
    @Description("Тест сортировки даты на странциы Все тесты. ЛКК")
    public void sortTestsByDate(){
        login(config.clientUsername, config.clientPass);

        ClientTestsPage clientTestsPage = new ClientTestsPage(driver, wait);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=MuiTable-root]")));

        clientTestsPage.clickInterviewCheckBox();
        clientTestsPage.parseTestsDate();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/headersBtnUrls.csv")
    @Description("Тест перехода по вкладкам. Лэндинг")
    public void goToHeaderTabs(String tabName, String url){
        driver.get(config.baseUrl);
        wait.until(ExpectedConditions.visibilityOfElementLocated(UX_TESTING_BUTTON));
        LandingPage landingPage = new LandingPage(driver, wait);
        landingPage.clickHeaderButton(tabName);
        Assertions.assertEquals(driver.getCurrentUrl(), url);
        driver.quit();
    }
}