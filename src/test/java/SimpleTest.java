import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import javax.swing.*;
import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class SimpleTest {
    private WebDriver driver = new ChromeDriver();

////    dom example
//    <span>
//    <a class="j-class" name="j-name", id="j-id">Press button</a>
//    </span>
//    @FindBy(id = "j-id")
//    @FindBy(className = "j-class")
//    @FindBy(name = "j-name")
//    @FindBy(linkText = "Press button")
//    @FindBy(partialLinkText = "button")
//    @FindBy(tagName = "a")
//    WebElement webElement;

    //css examples https://www.w3schools.com/cssref/css_selectors.asp
//    @FindBy(css = "#j-id")
//    @FindBy(css = ".j-class")
//    @FindBy(css = "[name='j-name']")
//    @FindBy(css = "a")
//    @FindBy(css = "a[name='j-name']")
//    @FindBy(css = "span a[class='j-class']")
//    @FindBy(css = "span [id*='id']")
//    @FindBy(css = "[name$='name']")
//    WebElement webElement;

    //xpath example
    //     //-descedant-or-self     ..-parent   @-attribute
    //    <span>
//    <a class="j-class" name="j-name", id="j-id">Press link</a>
//    <a class="j-class" name="j-name", id="j-id">Press link</a>
//    </span>
//    @FindBy(xpath = "//*[@id='j-id']")
//    @FindBy(xpath = "//*[@class='j-class']")
//    @FindBy(xpath = "//*[@name ='j-name']")
//    @FindBy(xpath = "//a[text() ='Press link']")
//    @FindBy(xpath = "//a[contains(.,'Press link')]")
//    @FindBy(xpath = "a")
//    @FindBy(xpath = "//a[@name='j-name']")
//    @FindBy(xpath = "//span//a[@class='j-class']")
//    @FindBy(xpath = "//span//*[contains(@id,''id)]")
//    @FindBy(xpath = "//*[ends-with(@name,name)]")
//    @FindBy(xpath = "//*[@id='j-id']//..//")
//    @FindBy(xpath = "//*[@class='j-class'][2]")
    //    WebElement webElement;



    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.close();
    }

    @DataProvider
    private Object[][] correctData() {
        return new Object[][]{{1, "Roman"}, {2, "Admin"}};
    }

    @Test(
            dataProvider = "correctData"
//            ,dataProviderClass = DataProviders.class
//            invocationCount = 3, //количество запусков
//            threadPoolSize = 3 //количество потоков
//            timeOut = 5
//            enabled = true
//            dependsOnMethods =
//            priority = 1
//            groups = {"smoke, regression"}
    )
    public void simpleTest(int num, String name) {
        driver.navigate().to("https://yandex.ru");
        driver.manage().window().maximize(); //setSize(new Dimension(1024,768));
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//        driver.manage().deleteAllCookies();
//        System.out.println(driver.getWindowHandle());
//        driver.switchTo().window(windowHandler);
//        driver.switchTo().alert();
        driver.navigate().refresh();
//        driver.navigate().forward();
//        driver.navigate().back();

        //dom
//        WebElement webElement = driver.findElement(By.id(""));
//        WebElement webElement = driver.findElement(By.name(""));
//        WebElement webElement = driver.findElement(By.className(""));
//        WebElement webElement = driver.findElement(By.tagName(""));
//
//
//        //css
//        driver.findElement(By.cssSelector(""));
//
//        //xpath
//        driver.findElement(By.xpath(""));
//
//        //webElement
//        //actions
//        webElement.click();
//        webElement.sendKeys("");
//        webElement.clear();
//        //for asserts
//        webElement.getText();
//        webElement.getAttribute("");
//        webElement.getCssValue("font-size");
//        webElement.isDisplayed();//often
//        webElement.isEnabled();//rarely
//        webElement.isSelected();//radio button, check box

//        //actions builder
//        Actions action = new Actions(driver);
//        action.sendKeys("").perform();

        //js executor
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
//        js.executeScript("alert('Hi!');");

        //screenshots
        TakesScreenshot sc = (TakesScreenshot) driver;
        sc.getScreenshotAs(OutputType.FILE);

        //asserts
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.getTitle().equals("Хрендикс"), "Ошибочка вышла не Хрендикс");
        assertEquals(driver.getTitle(), "Яндекс");
        softAssert.assertAll("все пропало");
    }

//    @Test
//    public void capabilitiesExample() {
//        DesiredCapabilities cap = DesiredCapabilities.chrome();
//        cap.setJavascriptEnabled(true);
//        new ChromeDriver(cap);
//    }
}
