package org.uxcrowd.education.ui_tests.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage extends AbstractPage{

    @FindBy(css = "#header-lk-button")
    private WebElement headerLoginButton;

    @FindBy(css = "#login")
    private WebElement loginInput;

    @FindBy(css = "[name=password]")
    private WebElement passwordInput;

    @FindBy(css = "[ng-click=\"login()\"]")
    private WebElement submitBtn;

    public LandingPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Нажать кнопку 'Войти' в хедере")
    public void clickHeaderLoginButton(){
        headerLoginButton.click();
    }

    @Step("Заполнить поле ввода логина")
    public void fillLoginInput(String text){
        loginInput.clear();
        loginInput.sendKeys(text);
    }

    @Step("Заполнить поле ввода пароля")
    public void fillPasswordInput(String text){
        passwordInput.clear();
        passwordInput.sendKeys(text);
    }

    @Step("Нажать кнопку отправить")
    public void submitForm(){
        submitBtn.click();
    }
}