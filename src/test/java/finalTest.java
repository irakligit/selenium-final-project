import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Test
public class finalTest {
    @Test
    public static void test() throws MalformedURLException, InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor)driver;
        driver.get("http://automationpractice.com/index.php");

        //select T-shirt
        WebElement T_shirts = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li/ul/li/ul/li/a"));
        js.executeScript("arguments[0].click();",T_shirts);
        Actions action = new Actions(driver);
        js.executeScript("window.scrollBy(0,500)");
        WebElement Quick = driver.findElement(By.cssSelector("[itemprop = 'image']"));
        action.moveToElement(Quick).perform();






    }
}
