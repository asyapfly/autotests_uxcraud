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

    @FindBy(xpath = "/html/body/div[1]/div/div/div/div/div[1]/section[1]/label")
    private WebElement regButton;

    @FindBy(xpath = "/html/body/div[1]/div/div/div/div/div[2]/section[2]/div[2]/button")
    private WebElement regTesterButton;

    @FindBy(css = "#emails")
    private WebElement TesterEmailInput;

    @FindBy(xpath = "//*[@id=\"form_register_customer\"]/button")
    private WebElement RegisterTester;

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

    @Step("Нажать кнопку Зарегистрироваться")
    public void clickRegBtn(){
        regButton.click();
    }
    @Step("Нажать кнопку Зарегистрироваться")
    public void clickTesterRegBtn(){
        regTesterButton.click();
    }
    @Step("ВВести емаил (email)")
    public void InputTester(String email){
        TesterEmailInput.sendKeys(email);
    }
    @Step("Зарегистрировать Тестировщика")
    public void regTester(){
        RegisterTester.click();
    }




}