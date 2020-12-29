import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.net.MalformedURLException;


@Test
public class finalTest {
    @Test
    public static void test() throws MalformedURLException, InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor)driver;
        driver.get("http://automationpractice.com/index.php");

        //select T-shirt
        WebElement T_shirts = driver.findElement(By.cssSelector("ul.submenu-container a[title='T-shirts']"));
        js.executeScript("arguments[0].click();",T_shirts);
        Actions action = new Actions(driver);
        js.executeScript("window.scrollBy(0,500)");
        WebElement Quick = driver.findElement(By.cssSelector("[itemprop = 'image']"));
        action.moveToElement(Quick).perform();
        driver.findElement(By.className("quick-view")).click();

        //wait and switchTo frame
        new WebDriverWait(driver, 300).ignoring(StaleElementReferenceException.class)
                .ignoring(WebDriverException.class)
                .until(ExpectedConditions.visibilityOf((driver.findElement(By.className("fancybox-iframe")))));
        driver.switchTo().frame(driver.findElement(By.className("fancybox-iframe")));

        //hover and check
        WebElement src_1 = driver.findElement(By.id("bigpic"));
        src_1.getAttribute("src");

        WebElement image_1 = driver.findElement(By.id("thumb_1"));
        WebElement image_2 = driver.findElement(By.id("thumb_2"));
        WebElement image_3 = driver.findElement(By.id("thumb_3"));
        WebElement image_4 = driver.findElement(By.id("thumb_4"));

        action.moveToElement(image_1).moveToElement(image_2).moveToElement(image_3)
                .moveToElement(image_4).perform();
        WebElement src_2 = driver.findElement(By.id("bigpic"));
        src_2.getAttribute("src");

        if (src_1 != src_2){
            System.out.println("image is changed");
        }else {
            System.out.println("image is not change");
        }


















    }
}
