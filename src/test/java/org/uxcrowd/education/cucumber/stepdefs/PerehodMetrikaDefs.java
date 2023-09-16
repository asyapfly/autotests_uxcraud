package org.uxcrowd.education.cucumber.stepdefs;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.uxcrowd.education.config.ApplicationConfig;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class PerehodMetrikaDefs {
    ApplicationConfig config = new ApplicationConfig();

    @Дано("пользователь заходит на главную страницу")
    public void OpPage() {
        open("/");
    }


    @Затем("кликает кнопку {string} в хедере")
    public void clickStrUX(String arg0) {
        $("#nl-header-links > li:nth-child(1) > a")
                .should(Condition.enabled)
                .click();
    }


    @Затем("кликает кнопку {string} на странице")
    public void clickStrInstr(String arg0) {
        $("#top > div.new_main_block.ng-scope > div > section.nl-about-section-info.aos-init.aos-animate > div > div > div:nth-child(3) > ul > li.active > span")
                .should(Condition.enabled)
                .click();
    }


    @Затем("кликает кнопку {string} на странице")
    public void clickStrMetrica(String arg0) {
        $("#top > div.new_main_block.ng-scope > div > section.nl-about-section-info.aos-init.aos-animate > div > div > div:nth-child(3) > ul > li.active > ul > li:nth-child(5) > a")
                .should(Condition.enabled)
                .click();
    }


}
