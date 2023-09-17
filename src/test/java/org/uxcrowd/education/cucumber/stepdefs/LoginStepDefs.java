package org.uxcrowd.education.cucumber.stepdefs;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.uxcrowd.education.config.ApplicationConfig;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class LoginStepDefs {
    ApplicationConfig config = new ApplicationConfig();

    private String typeOfUser;

    @Дано("пользователь заходит на главную страницу")
    public void openPage() {
        open("/");
    }

    @Затем("кликает кнопку {string} в хедере")
    public void clickLogin(String arg0) {
        $("#header-lk-button")
                .should(Condition.enabled)
                .click();
    }

    @Когда("^вводит логин \"(.+)\"$")
    public void inputLogin(String login) {
        $("#login").val(login);
    }

    @И("вводит пароль {string}")
    public void inputPassword(String password) {
        $("[name=password]").val(password);
    }

    @И("нажимает кнопку {string}")
    public void clickSubmit(String arg0) {
        $("[ng-click=\"login()\"]")
                .should(Condition.enabled)
                .click();
    }

    @Если("пользователь {string}")
    public void checkThatClient(String type) {
        Assertions.assertEquals(type, "client");
    }

    @То("проверяем URL ЛК в зависимости от типа пользователя \"(.+)\"$")
    public void checkThatClientURL(String typeOfUser) {

        if(typeOfUser.equals("client")){
            Selenide.Wait().until(visibilityOfElementLocated(By.cssSelector("[data-testid=\"Header profile settings\"]")));
            webdriver().shouldHave(url(config.clientPageURL));
        } else if (typeOfUser.equals("tester")) {
            Selenide.Wait().until(visibilityOfElementLocated(By.cssSelector("a[href=\"/app-tester-home/tester-profile\"]")));
            webdriver().shouldHave(url(config.testerPageURL));
        }
        else Assertions.fail();
    }

    @То("чистим куки")
    public void cleanCookie() {
        Selenide.clearBrowserCookies();
    }

    @Дано("пользователь заходит на лендинг")
    public void пользовательЗаходитНаЛендинг() {
        open("/");
    }

    @Затем("кликает в хедере кнопку {string}")
    public void clickUXHeaderBtn(String arg0) {
        $("#nl-header-links > li:nth-child(1) > a")
                .should(Condition.enabled)
                .click();
    }

    @Затем("кликает спойлер {string}")
    public void spoilerClick(String arg0) {
        $x("//*[@id=\"top\"]/div[2]/div/section[2]/div/div/div[3]/ul/li[1]").scrollTo();
        $x("//*[@id=\"top\"]/div[2]/div/section[2]/div/div/div[3]/ul/li[5]").click();
    }

    @Затем("кликает кнопку Метрики SUS и SUPR-Qна странице")
    public void clickStrMetrica() {
        $("li a[href=\"#about_metrics\"]")
                .should(Condition.enabled)
                .click();
    }

    @Затем("кликает ссылку Узнать подробнее")
    public void clickToMetricsPage() {
        $x("//*[@id=\"about_metrics\"]/div/ul/li[4]/a")
                .should(Condition.enabled)
                .click();
    }

    @Тогда("попадает на страницу с метриками")
    public void goToMetricsLanding() {
        webdriver().shouldHave(url("https://dev-t.uxcrowd.ru/metrics"));
    }
}
