import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class FirstTest {

   @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    private WebDriver driver;

    @BeforeMethod
    public void beforeTest() {
       // WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void testVitaliiDmitrenko() {

        // System.setProperty("webdriver.chrome.driver","C:/Users/medstar/Downloads/temp/chromedriver.exe");


        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.gismeteo.ua/weather-kharkiv-5053/");
        WebElement tenDaysButton = driver.findElement(By.xpath("//a[@href='/weather-kharkiv-5053/10-days/']"));
        tenDaysButton.click();

        WebElement tenDaysTitle = driver.findElement(By.xpath("//div[@class='pageinfo_title index-h1']//h1"));
        Assert.assertEquals(tenDaysTitle.getText(), "Погода в Харькове на 10 дней");

        WebElement monthButton = driver.findElement(By.xpath("//a[@href='/weather-kharkiv-5053/month/']"));
        monthButton.click();

        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

        WebElement monthTitle = driver.findElement(By.xpath("//div[@class='pageinfo_title index-h1']//h1"));
        Assert.assertEquals(monthTitle.getText(), "Погода в Харькове на месяц");
    }

    public static void newClick (WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);

    }

    @Test
    public void testAutomationPracticeForm() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://demoqa.com/automation-practice-form");

        WebElement lastName = driver.findElement(By.id("lastName"));
        driver.findElement(By.id("firstName")).sendKeys("Vitalii");
        lastName.sendKeys("Petrosian");
        lastName.clear();
        lastName.sendKeys("Aqanovich");
        driver.findElement(By.id("userEmail")).sendKeys("Aqanovich@mailinator.com");
        Thread.sleep(2000);

//        WebElement radioButton = driver.findElement(By.xpath("//input[@name = 'gender' and @value = 'Male']//ancestor::div[contains (@class , 'custom-control')]"));
        WebElement radioButton = driver.findElement(By.xpath("//input[@name = 'gender' and @value = 'Male']"));
        newClick(driver, radioButton);
        Thread.sleep(2000);

       /* List<WebElement> gender = driver.findElements(By.xpath("//input[@type = 'radio']"));
        gender.size();
        for (int i = 0; i < gender.size(); i++) {
            String val = gender.get(i).getAttribute("value");
            Thread.sleep(2000);
            if (val.equalsIgnoreCase("male")){
                Thread.sleep(2000);
              //  gender.get(i).click();
                newClick(driver, gender.get(i));

            }
            Thread.sleep(2000);
        }*/



    }



        @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

}
