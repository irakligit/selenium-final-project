import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Random;


import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;


@Test
public class finalTest {
    WebDriver driver;

    /**
     * This function will execute before each Test tag in testng.xml
     *
     * @param browser
     * @throws Exception
     */
    @Parameters({"browser"})
    @BeforeMethod()
    public void setup(String browser) throws Exception {
        if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            JavascriptExecutor js = (JavascriptExecutor) driver;
        } else if (browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };

    }


    @Test
    public void testParameterWithXML() throws InterruptedException, MalformedURLException {
        String[] before_add = new String[2];
        String[] after_add = new String[2];
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get("http://automationpractice.com/index.php");


        //select T-shirt

        Actions action = new Actions(driver);

        WebElement T_shirts = driver.findElement(By.cssSelector("ul.submenu-container a[title='T-shirts']"));

        js.executeScript("arguments[0].click();", T_shirts);

        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOf((driver.findElement(By.className("container")))));


        js.executeScript("window.scrollBy(0,500)");


        WebElement first_title = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div/h5/a"));
        before_add[0] = first_title.getText();

        WebElement Quick = driver.findElement(By.xpath("//img[@class='replace-2x img-responsive' and @title='Faded Short Sleeve T-shirts']"));
        action.moveToElement(Quick).perform();
        driver.findElement(By.className("quick-view")).click();

        //wait and switchTo frame
        new WebDriverWait(driver, 30).ignoring(StaleElementReferenceException.class)
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

        //check
        if (src_1 != src_2) {
            System.out.println("image is changed");
        } else {
            System.out.println("image is not change");
        }
        //add to cart and check
        driver.findElement(By.className("icon-plus")).click();
        WebElement dropdown_menu = driver.findElement(By.name("group_1"));
        Select choose = new Select(dropdown_menu);
        choose.selectByValue("2");
        driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]//span[text() = 'Add to cart']")).click();
        driver.switchTo().defaultContent();


        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOf((driver.findElement(By.className("layer_cart_cart")))));
        driver.findElement(By.cssSelector("[class^='continue']:last-of-type")).click();
        driver.findElement(By.xpath("//*[@href = \"http://automationpractice.com/\"]")).click();

        WebElement Casual_Dresses = driver.findElement(By.cssSelector("ul.submenu-container a[title='Casual Dresses']"));
        js.executeScript("arguments[0].click();", Casual_Dresses);
        WebElement scroll_2 = driver.findElement(By.xpath("//*[@class=\"selectProductSort form-control\"]"));
        js.executeScript("arguments[0].scrollIntoView();", scroll_2);
        WebElement second_title = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div/h5/a"));
        before_add[1] = second_title.getText();

        WebElement hover_2 = driver.findElement(By.cssSelector("[title = 'Printed Dress']"));
        action.moveToElement(hover_2).perform();
        driver.findElement(By.cssSelector("[title = 'Add to cart']")).click();
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")))));
        driver.findElement(By.cssSelector("[title = 'Proceed to checkout']")).click();
        js.executeScript("window.scrollBy(0,500)");
        WebElement title_3 = driver.findElement(By.xpath("//*[@id=\"product_1_3_0_0\"]/td/p/a"));
        after_add[0] = title_3.getText();

        WebElement title_4 = driver.findElement(By.xpath("//*[@id=\"product_3_13_0_0\"]/td/p/a"));
        after_add[1] = title_4.getText();

        //check_2
        if (before_add[0].equals(after_add[0]) && before_add[1].equals(after_add[1])) {
            System.out.println("items added");
        } else {
            System.out.println("items not added");
        }

        driver.findElement(By.xpath("//*[@href=\"http://automationpractice.com/index.php?controller=order&step=1\"]"))
                .click();

        //CREATE AN ACCOUNT
        WebElement scroll_3 = driver.findElement(By.className("page-subheading"));
        js.executeScript("arguments[0].scrollIntoView();", scroll_3);

        //random-string
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String randomString = "";
        int length = 5;
        Random rand = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++){
            text[i] = characters.charAt(rand.nextInt(characters.length()));
        }
        for (int i = 0; i < text.length; i++){
            randomString +=text[i];
        }
        driver.findElement(By.xpath("//*[contains(@name,'email_create')]")).sendKeys(randomString + "@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]")).click();



        //registration
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("uniform-id_gender1")));
        driver.findElement(By.id("uniform-id_gender1")).click();
        driver.findElement(By.cssSelector("[id = 'customer_firstname']")).sendKeys(randomString);
        driver.findElement(By.id("customer_lastname")).sendKeys(randomString);
        driver.findElement(By.id("passwd")).sendKeys(randomString);
        //dropdowns
        WebElement days = driver.findElement(By.id("days"));
        new Select(days).selectByValue("18");
        WebElement months = driver.findElement(By.id("months"));
        new Select(months).selectByValue("12");
        WebElement years = driver.findElement(By.id("years"));
        new Select(years).selectByValue("2000");
        //input
        driver.findElement(By.id("company")).sendKeys(randomString);
        driver.findElement(By.id("address1")).sendKeys(randomString);
        driver.findElement(By.id("address2")).sendKeys(randomString);
        driver.findElement(By.id("city")).sendKeys(randomString);
        //state
        WebElement state = driver.findElement(By.id("id_state"));
        new Select(state).selectByValue("3");
        //postal code
        String characters_2 = "012345678910";
        String randomString_2 = "";
        int length_2 = 5;
        Random rand_2 = new Random();
        char[] text_2 = new char[length_2];
        for (int i = 0; i < length_2; i++){
            text[i] = characters_2.charAt(rand_2.nextInt(characters_2.length()));
        }
        for (int i = 0; i < text_2.length; i++){
            randomString_2 +=text[i];
        }
        driver.findElement(By.id("postcode")).sendKeys(randomString_2);
        driver.findElement(By.id("phone")).sendKeys(randomString_2);
        driver.findElement(By.id("phone_mobile")).sendKeys(randomString_2);
        driver.findElement(By.id("submitAccount")).click();
        driver.findElement(By.name("processAddress")).click();
        driver.findElement(By.name("processCarrier")).click();

        //alert check
        if(alertIsPresent()!=null){
            System.out.println("alert exists");
            action.click().perform();
        }else{
            System.out.println("alert does not exist");
        }
        driver.findElement(By.id("cgv")).click();
        driver.findElement(By.name("processCarrier")).click();
        WebElement price_1 = driver.findElement(By.id("total_price"));
        String firstPrice = price_1.getText();
        driver.findElement(By.className("cheque")).click();
        WebElement price_2 = driver.findElement(By.id("amount"));
        String secondPrice = price_2.getText();

        //price check
        if (secondPrice.equals(firstPrice)){
            System.out.println("The prices are the same");
        }else {
            System.out.println("Prices are different");
        }
        driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button")).click();
        driver.findElement(By.xpath("//*[@href=\"http://automationpractice.com/index.php?controller=contact\"]")).click();


        //form
        js.executeScript("window.scrollBy(0,500)");
        WebElement dropdown_menu_3 = driver.findElement(By.name("id_contact"));
        Select choose_3 = new Select(dropdown_menu_3);
        choose_3.selectByValue("2");
        WebElement dropdown_menu_4 = driver.findElement(By.name("id_order"));
        Select choose_4 = new Select(dropdown_menu_4);
        choose_4.selectByValue("0");

        WebElement addFile = driver.findElement(By.cssSelector("#fileUpload"));
        File file = new File((System.getProperty("user.dir") + "/src/files/1.jpg"));
        String picturePath = file.getAbsolutePath();
        addFile.sendKeys(picturePath);
        driver.findElement(By.id("message")).sendKeys("finish");
        driver.findElement(By.id("submitMessage")).click();
        driver.quit();





    }




}