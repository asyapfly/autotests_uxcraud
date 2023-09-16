package org.uxcrowd.education.ui_tests.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientTestsPage extends AbstractPage{

    private static final int TEST_NAME_COL = 0;
    private static final int TYPE_COL = 1;
    private static final int ENVIRE_COL = 2;
    private static final int QUANTITY_COL = 3;
    private static final int STATUS_COL = 4;
    private static final int USERS_FROM_COL = 5;
    private static final int DATE_COL = 6;
    private static final int ACTIONS_COL = 7;

    @FindBy(css = "[class=MuiTable-root]")
    private WebElement vanillaTestsTable; //user table for vanilla Selenium example

    @FindBy(css = "[value=ALL_INTERVIEWS]")
    private WebElement interviewCheckBox;

    @FindBy(css = "span[sorting]")
    private WebElement dateSortingBtn;

    public ClientTestsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private List<WebElement> findTestsTrs() {
        return vanillaTestsTable.findElements(By.cssSelector("tbody tr"));
    }

    @Step("Нажать чек-бокс 'Все интервью'}")
    public void clickInterviewCheckBox(){
        interviewCheckBox.click();
    }

    @Step("Нажать кнопку сортировки по дате}")
    public void clickDateSortingBtn(){
        dateSortingBtn.click();
    }


    @Step("Спарсить даты создания тестов со страницы Все тесты")
    public List<Date> parseTestsDate() {
        List<WebElement> testTrs = findTestsTrs();
        List<Date> testsDateList = new ArrayList<>();
        for (WebElement testTr : testTrs) {
            WebElement dateColumn = testTr.findElements(By.cssSelector("td")).get(DATE_COL);

            String strDate = dateColumn.getText();

            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

            Date date = null;
            try {
                date = formatter.parse(strDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            testsDateList.add(date);
        }
        //System.out.println(testsDateList);
        return testsDateList;
    }
}
