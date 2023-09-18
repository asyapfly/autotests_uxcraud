package org.uxcrowd.education.ui_tests.page;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TesterProfilePage extends AbstractPage {

    public static final By.ByCssSelector SUCCESS_ALERT_LOCATOR = new By.ByCssSelector("div[ng-show=profileSaved]");

    @FindBy(css = "input[ng-model=\"profile.fio\"]")
    private WebElement fioInput;

    @FindBy(css = "#tester-profile-data")
    private WebElement birthdateInput;

    @FindBy(css = "input[ng-model=\"profile.city\"]")
    private WebElement cityInput;

    @FindBy(css = "[data-testid=\"Profile tester menu button\"]")
    private WebElement profileMenuBtn;

    @FindBy(css = "button[type=submit]")
    private WebElement submitBtn;

    @FindBy(css = "div[ng-show=profileSaved]")
    public WebElement successAlert;

    public TesterProfilePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Нажать на кнопку меню 'Профиль'")
    public void clickProfileMenuBtn() {
        profileMenuBtn.click();
    }

    @Step("Всести в форму ФИО {fio}")
    public void fillFioInput(String fio) {
        fioInput.clear();
        fioInput.sendKeys(fio);
    }

    @Step("Ввести в форму дату рождения {date}")
    public void fillDateInput(String date) {
        birthdateInput.clear();
        birthdateInput.sendKeys(date);
    }

    @Step("Всести в форму населенный пункт {fio}")
    public void fillCityInput(String city) {
        cityInput.clear();
        cityInput.sendKeys(city);
    }

    @Step(" Нажать кнопку 'Сохранить'")
    public void saveBtnClick() {
        submitBtn.click();
    }

}
