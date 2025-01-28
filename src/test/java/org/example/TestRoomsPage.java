package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestRoomsPage {

    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
//        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1424, 968)); // Rezoluție mai mică

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void verifyPageDescription() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/rooms");
        System.out.println("Page is loaded successfully");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement paragraphElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i6lwrp17")));

        // Obține textul elementului
        String paragraphText = paragraphElement.getText();
        System.out.println("Paragraph text: " + paragraphText);

        String expectedText1 = "I'm a paragraph. Click here to add your own text and edit me. It’s easy. Just click “Edit Text” or double click me to add your own content and make changes to the font. Feel free to drag and drop me anywhere you like on your page. I’m a great place for you to tell a story and let your users know a little more about you.";
        String expectedText2="I'm a paragraph. Click here to add your own text and edit me. I’m a great place for you to tell a story and let your users know a little more about you.";
        // Verifică dacă textul paragrafului conține informații relevante
        if (!paragraphText.isEmpty() && !paragraphText.equals(expectedText1)&& !paragraphText.equals(expectedText2)) {
            System.out.println("The description paragraph contains relevant information");
        } else {
            System.out.println("The description paragraph doesn't contain relevant information");
            Assert.fail("Paragraph text does not contain relevant information.");
        }
    }

    @Test
    public void verifyStandardSuiteImage() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/rooms");
        System.out.println("Page is loaded successfully");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'i6klgqap_0\']/iframe")));
        //WebElement iframe = driver.findElement(By.xpath("//*[@id=\'i6klgqap_0\']/iframe"));
        driver.switchTo().frame(iframe);


        WebElement StandardSuite = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[1]/div/div[2]/div[1]/h3/a")));

        Assert.assertEquals(StandardSuite.getText(),"Standard Suite","The 'Standard Suite' room is not displayed!");
        System.out.println("The 'Standard Suite' room is displayed!");


        WebElement StandardSuiteImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[1]/div/div[1]/img")));
        String src = StandardSuiteImage.getAttribute("src");
        System.out.println("Image src is: "+src);
        if(src!=null&& !src.equals("https://static.wixstatic.com/media/fde015_cb4dcccb4258499a894623f5282baa98.png/v1/fill/w_240,h_170,q_85,usm_0.66_1.00_0.01/fde015_cb4dcccb4258499a894623f5282baa98.png"))
        {
            System.out.println("The 'Standard Suite' image is displayed!");

        }
        else
        {
            Assert.fail( "The 'Standard Suite' image is not displayed!");
        }
        //fde015_cb4dcccb4258499a894623f5282baa98.png


//        StandardSuiteImage.click();
//
//        wait.until(ExpectedConditions.urlToBe("https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4"));
//        String currentUrl = driver.getCurrentUrl();
//        String expectedUrl = "https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4";
//        Assert.assertEquals(currentUrl, expectedUrl, "The URL is incorrect after clicking the Standard button!");

    }


    @Test
    public void verifyCottageImage() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/rooms");
        System.out.println("Page is loaded successfully");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'i6klgqap_0\']/iframe")));
        //WebElement iframe = driver.findElement(By.xpath("//*[@id=\'i6klgqap_0\']/iframe"));
        driver.switchTo().frame(iframe);


        WebElement Cottage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[2]/div/div[2]/div[1]/h3")));

        Assert.assertEquals(Cottage.getText(),"Cottage","The 'Cottage' room is not displayed!");
        System.out.println("The 'Cottage' room is displayed!");


        WebElement CottageImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[2]/div/div[1]/img")));
        String src = CottageImage.getAttribute("src");
        System.out.println("Image src is: "+src);
        if(src!=null&& !src.equals("https://static.wixstatic.com/media/fde015_cb4dcccb4258499a894623f5282baa98.png/v1/fill/w_240,h_170,q_85,usm_0.66_1.00_0.01/fde015_cb4dcccb4258499a894623f5282baa98.png"))
        {
            System.out.println("The 'Cottage' image is displayed!");

        }
        else
        {
            Assert.fail( "The 'Cottage' image is not displayed!");
        }
        //fde015_cb4dcccb4258499a894623f5282baa98.png


//        CottageImage.click();
//
//        wait.until(ExpectedConditions.urlToBe("https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4"));
//        String currentUrl = driver.getCurrentUrl();
//        String expectedUrl = "https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4";
//        Assert.assertEquals(currentUrl, expectedUrl, "The URL is incorrect after clicking the Standard button!");

    }


    @Test
    public void verifyClassicAppImage() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/rooms");
        System.out.println("Page is loaded successfully");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'i6klgqap_0\']/iframe")));
        //WebElement iframe = driver.findElement(By.xpath("//*[@id=\'i6klgqap_0\']/iframe"));
        driver.switchTo().frame(iframe);


        WebElement ClassicApp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[3]/div/div[2]/div[1]/h3")));

        Assert.assertEquals(ClassicApp.getText(),"Classic App","The 'Classic App' room is not displayed!");
        System.out.println("The 'Classic App' room is displayed!");


        WebElement ClassicAppImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[3]/div/div[1]/img")));
        String src = ClassicAppImage.getAttribute("src");
        System.out.println("Image src is: "+src);

        if(src!=null&& !src.equals("https://static.wixstatic.com/media/fde015_cb4dcccb4258499a894623f5282baa98.png/v1/fill/w_240,h_170,q_85,usm_0.66_1.00_0.01/fde015_cb4dcccb4258499a894623f5282baa98.png"))
        {
            System.out.println("The 'Classic App' image is displayed!");
        }
        else
        {
            Assert.fail( "The 'Classic App' image is not displayed!");
        }
        //fde015_cb4dcccb4258499a894623f5282baa98.png


//        ClassicAppImage.click();
//
//        wait.until(ExpectedConditions.urlToBe("https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4"));
//        String currentUrl = driver.getCurrentUrl();
//        String expectedUrl = "https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4";
//        Assert.assertEquals(currentUrl, expectedUrl, "The URL is incorrect after clicking the Standard button!");

    }

    @Test
    public void verifyStandardSuiteLink() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/rooms");
        System.out.println("Page is loaded successfully");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'i6klgqap_0\']/iframe")));

        driver.switchTo().frame(iframe);
//        WebElement standardSuiteLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.s-title[stranslate='Standard Suite']")));
//        Assert.assertTrue(standardSuiteLink.isDisplayed(), "The 'Standard Suite' link is not displayed!");

        WebElement StandardSuite = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[1]/div/div[2]/div[1]/h3/a")));

        Assert.assertEquals(StandardSuite.getText(),"Standard Suite","The 'Standard Suite' title is not displayed!");
        System.out.println("The 'Standard Suite' title is displayed!");
        StandardSuite.click();

        wait.until(ExpectedConditions.urlToBe("https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4"));
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4";
        Assert.assertEquals(currentUrl, expectedUrl, "The URL is incorrect after clicking the Standard Suite button!");
        System.out.println("Standard Suite room's page with details is displayed");

    }

    @Test
    public void verifyCottageLink() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/rooms");
        System.out.println("Page is loaded successfully");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'i6klgqap_0\']/iframe")));

        driver.switchTo().frame(iframe);

        WebElement Cottage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[2]/div/div[2]/div[1]/h3")));

        Assert.assertEquals(Cottage.getText(),"Cottage","The 'Cottage' title is not displayed!");
        System.out.println("The 'Cottage' title is displayed!");
        Cottage.click();

        wait.until(ExpectedConditions.urlToBe("https://ancabota09.wixsite.com/intern/rooms/rooms/4e2820f3-0564-4bd0-9258-e7594d617297"));
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://ancabota09.wixsite.com/intern/rooms/rooms/4e2820f3-0564-4bd0-9258-e7594d617297";
        Assert.assertEquals(currentUrl, expectedUrl, "The URL is incorrect after clicking the Cottage button!");
        System.out.println("Cottage room's page with details is displayed");

    }

    @Test
    public void verifyClassicAppLink() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/rooms");
        System.out.println("Page is loaded successfully");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'i6klgqap_0\']/iframe")));

        driver.switchTo().frame(iframe);
//        WebElement ClassicAppLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.s-title[stranslate='Classic App']")));
//        Assert.assertTrue(ClassicAppLink.isDisplayed(), "The 'Classic App' link is not displayed!");

        WebElement ClassicApp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[3]/div/div[2]/div[1]/h3")));

        Assert.assertEquals(ClassicApp.getText(),"Classic App","The 'Classic App' title is not displayed!");
        System.out.println("The 'Classic App' title is displayed!");
        ClassicApp.click();

        wait.until(ExpectedConditions.urlToBe("https://ancabota09.wixsite.com/intern/rooms/rooms/1739582a-003e-49e7-a9e6-b6fdb55a9027"));
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://ancabota09.wixsite.com/intern/rooms/rooms/1739582a-003e-49e7-a9e6-b6fdb55a9027";
        Assert.assertEquals(currentUrl, expectedUrl, "The URL is incorrect after clicking the Classic App button!");
        System.out.println("Classic App room's page with details is displayed");

    }


    @Test
    public void verifyStandardSuiteMoreInfoButton() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/rooms");
        System.out.println("Page is loaded successfully");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'i6klgqap_0\']/iframe")));

        driver.switchTo().frame(iframe);
//        WebElement standardSuiteLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.s-title[stranslate='Standard Suite']")));
//        Assert.assertTrue(standardSuiteLink.isDisplayed(), "The 'Standard Suite' link is not displayed!");

        WebElement StandardSuite = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[1]/div/div[2]/div[4]/button")));

        Assert.assertTrue(StandardSuite.isDisplayed());
        System.out.println("The More Info button is displayed!");

        String text = StandardSuite.getText();
        Assert.assertEquals(text, "More Info", "Text not correct!");
        System.out.println("The name of the button is More Info");

        StandardSuite.click();

        wait.until(ExpectedConditions.urlToBe("https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4"));
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4";
        Assert.assertEquals(currentUrl, expectedUrl, "The URL is incorrect after clicking the Standard Suite button!");
        System.out.println("Standard Suite room's page with details is displayed");

    }

    @Test
    public void verifyCottageMoreInfoButton() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/rooms");
        System.out.println("Page is loaded successfully");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'i6klgqap_0\']/iframe")));

        driver.switchTo().frame(iframe);

        WebElement Cottage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[2]/div/div[2]/div[4]/button")));

        Assert.assertTrue(Cottage.isDisplayed());
        System.out.println("The More Info button is displayed!");

        String text = Cottage.getText();
        Assert.assertEquals(text, "More Info", "Text not correct!");
        System.out.println("The name of the button is More Info");

        Cottage.click();

        wait.until(ExpectedConditions.urlToBe("https://ancabota09.wixsite.com/intern/rooms/rooms/4e2820f3-0564-4bd0-9258-e7594d617297"));
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://ancabota09.wixsite.com/intern/rooms/rooms/4e2820f3-0564-4bd0-9258-e7594d617297";
        Assert.assertEquals(currentUrl, expectedUrl, "The URL is incorrect after clicking the Cottage button!");
        System.out.println("Cottage room's page with details is displayed");

    }

    @Test
    public void verifyClassicAppMoreInfoButton() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/rooms");
        System.out.println("Page is loaded successfully");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'i6klgqap_0\']/iframe")));

        driver.switchTo().frame(iframe);
//        WebElement ClassicAppLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.s-title[stranslate='Classic App']")));
//        Assert.assertTrue(ClassicAppLink.isDisplayed(), "The 'Classic App' link is not displayed!");

        WebElement ClassicApp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[3]/div/div[2]/div[4]/button")));

        Assert.assertTrue(ClassicApp.isDisplayed());
        System.out.println("The More Info button is displayed!");

        String text = ClassicApp.getText();
        Assert.assertEquals(text, "More Info", "Text not correct!");
        System.out.println("The name of the button is More Info");

        ClassicApp.click();

        wait.until(ExpectedConditions.urlToBe("https://ancabota09.wixsite.com/intern/rooms/rooms/1739582a-003e-49e7-a9e6-b6fdb55a9027"));
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://ancabota09.wixsite.com/intern/rooms/rooms/1739582a-003e-49e7-a9e6-b6fdb55a9027";
        Assert.assertEquals(currentUrl, expectedUrl, "The URL is incorrect after clicking the Classic App button!");
        System.out.println("Classic App room's page with details is displayed");

    }


    @Test
    public void verifySearchAgainButton() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/rooms");
        System.out.println("Page is loaded successfully");


        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));
        driver.switchTo().frame(iframe);

        WebElement searchWidget = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"hotel-container\"]/section/div/div")));


        if (searchWidget.isDisplayed()) {
            System.out.println("The search widget is displayed");
        } else {
            Assert.fail("The search widget is not displayed");
        }

        driver.findElement(By.id("check-in")).click();

        WebElement checkinFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("calendar")));

        if (checkinFrame.isDisplayed()) {
            System.out.println("The check In Calendar is displayed");
        } else {
            Assert.fail("The check In Calendar is not displayed");
        }

        LocalDate today = LocalDate.now();
        LocalDate threeDaysAfter = today.plusDays(3);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);

        String formattedDate = today.format(formatter);

        String xpath = String.format("//button[@aria-label='%s']", formattedDate);
        System.out.println(xpath);

        WebElement dateButtonCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        wait.withTimeout(Duration.ofSeconds(60));
        dateButtonCheckIn.click();

        WebElement checkinvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_in-value")));
        String formattedDate1 = today.format(newFormatter);

        Assert.assertEquals(checkinvalue.getText(), formattedDate1,"The selected date is NOT displayed in Check In box");
        System.out.println("The selected date is displayed in Check In box");

        wait.withTimeout(Duration.ofSeconds(30));
        WebElement checkOutFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[2]/div[2]/div")));

        if (checkOutFrame.isDisplayed()) {
            System.out.println("The check out Calendar is displayed");
        } else {
            Assert.fail("The check out Calendar is not displayed");
        }
        String newFormattedDate = threeDaysAfter.format(formatter);

        String newXPath = String.format("(//button[@aria-label='%s'])[2]", newFormattedDate);
        System.out.println(newXPath);

        WebElement dateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newXPath)));
        wait.withTimeout(Duration.ofSeconds(60));
        dateButton.click();

        WebElement checkOutvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_out-value")));
        String formattedDate2 = threeDaysAfter.format(newFormatter);

        Assert.assertEquals(checkOutvalue.getText(), formattedDate2,"The selected date is NOT displayed in Check Out box");
        System.out.println("The selected date is displayed in Check Out box");
//////////////////////////////////////////

        WebElement counterInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .value")));
//        WebElement counterInput = driver.findElement(By.cssSelector("#adults .value"));
        WebElement decrementButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .down")));
//        WebElement decrementButton = driver.findElement(By.cssSelector("#adults .down"));
        WebElement incrementButton = driver.findElement(By.cssSelector("#adults .up"));
        int counterValue = Integer.parseInt(counterInput.getText());

        if (counterValue == 1) {
            System.out.println("The value of adults counter is 1");
//            incrementButton.click();
//            int counterValue1 = Integer.parseInt(counterInput.getText());
//            if (counterValue1 == 2) {
//
//                System.out.println("The counter value is increases from 1 to 2");
//
//            } else
//            {
//                Assert.fail("The counter value is not increases from 1 to 2");
//            }

        } else
        {
            Assert.fail("The counter value is not 1");
        }

        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("s-button")));
        searchButton.click();

        String currentUrl = driver.getCurrentUrl();
        if(currentUrl.contains("https://ancabota09.wixsite.com/intern/rooms")) {
            System.out.println("The rooms page is loaded");
        }
        else {
            Assert.fail( "Bad redirect");
        }

        driver.switchTo().defaultContent(); //back to main

//        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));

        driver.switchTo().frame(iframe);
        wait.withTimeout(Duration.ofSeconds(60));

        WebElement checkinvalue1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check-in")));
        WebElement checkoutvalue1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check-out")));

        // Verifică dacă datele coincid
        if (Objects.equals(checkOutvalue.getText(), checkoutvalue1.getText()) && Objects.equals(checkinvalue.getText(), checkinvalue1.getText())) {
            System.out.println("with selected data");
        } else {
            System.out.println("not coresponding data...");
            System.out.println("Check-in extras: " + checkinvalue1.getText() + " vs Check-in așteptat: " + checkinvalue.getText());
            System.out.println("Check-out extras: " + checkoutvalue1.getText() + " vs Check-out așteptat: " + checkOutvalue.getText());
        }

//        driver.switchTo().frame(iframe);

        driver.switchTo().defaultContent(); //back to main
        WebElement iframe1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));

        driver.switchTo().frame(iframe1);
        wait.withTimeout(Duration.ofSeconds(60));

        WebElement divMare = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]")));

        // Verifică dacă există o listă <ul> în acest div
        boolean existaUl = divMare.findElements(By.tagName("ul")).size() > 0;

        // Afișează rezultatul
        if (existaUl) {
            System.out.println("and coresponding rooms!");
        } else {
            Assert.fail("but not coresponding rooms!");
        }


        //99999999999999999999999999   aceasi secventa de mai sus dar cu search again
        WebElement searchAgainWidget = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[5]/button")));


        if (searchAgainWidget.isDisplayed()) {
            System.out.println("The search again widget is displayed");
        } else {
            Assert.fail("The search again widget is not displayed");
        }

        driver.findElement(By.id("check-in")).click();

//        WebElement checkinFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("calendar")));

        if (checkinFrame.isDisplayed()) {
            System.out.println("The check In Calendar is displayed");
        } else {
            Assert.fail("The check In Calendar is not displayed");
        }

        LocalDate tomorrow = LocalDate.now().plusDays(5);
        LocalDate threeDaysAfter1 = tomorrow.plusDays(7);

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);
//        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);

        String formattedDateT = tomorrow.format(formatter);

        String xpathT = String.format("//button[@aria-label='%s']", formattedDateT);
        System.out.println(xpathT);

        WebElement dateButtonCheckInT = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathT)));
        wait.withTimeout(Duration.ofSeconds(60));
        dateButtonCheckInT.click();

        WebElement checkinvalueT = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_in-value")));
        String formattedDate1T = tomorrow.format(newFormatter);

        Assert.assertEquals(checkinvalueT.getText(), formattedDate1T,"The selected date is NOT displayed in Check In box");
        System.out.println("The selected date is displayed in Check In box");

        wait.withTimeout(Duration.ofSeconds(30));
        WebElement checkOutFrameT = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[2]/div[2]/div")));

        if (checkOutFrameT.isDisplayed()) {
            System.out.println("The check out Calendar is displayed");
        } else {
            Assert.fail("The check out Calendar is not displayed");
        }
        String newFormattedDateT = threeDaysAfter1.format(formatter);

        String newXPathT = String.format("(//button[@aria-label='%s'])[2]", newFormattedDateT);
        System.out.println(newXPathT);

        WebElement dateButtonT = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newXPathT)));
        wait.withTimeout(Duration.ofSeconds(60));
        dateButtonT.click();

        WebElement checkOutvalueT = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_out-value")));
        String formattedDate2T = threeDaysAfter1.format(newFormatter);

        Assert.assertEquals(checkOutvalueT.getText(), formattedDate2T,"The selected date is NOT displayed in Check Out box");
        System.out.println("The selected date is displayed in Check Out box");
//////////////////////////////////////////

        searchAgainWidget.click();

         currentUrl = driver.getCurrentUrl();
        if(currentUrl.contains("https://ancabota09.wixsite.com/intern/rooms")) {
            System.out.println("The rooms page is loaded");
        }
        else {
            Assert.fail( "Bad redirect");
        }

        driver.switchTo().defaultContent(); //back to main

//        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));

        driver.switchTo().frame(iframe);
        wait.withTimeout(Duration.ofSeconds(60));

         checkinvalue1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check-in")));
         checkoutvalue1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check-out")));

        // Verifică dacă datele coincid
        if (Objects.equals(checkOutvalueT.getText(), checkoutvalue1.getText()) && Objects.equals(checkinvalueT.getText(), checkinvalue1.getText())) {
            System.out.println("with selected data");
        } else {
            System.out.println("not coresponding data...");
            System.out.println("Check-in extras: " + checkinvalue1.getText() + " vs Check-in așteptat: " + checkinvalueT.getText());
            System.out.println("Check-out extras: " + checkoutvalue1.getText() + " vs Check-out așteptat: " + checkOutvalueT.getText());
        }

//        driver.switchTo().frame(iframe);

        driver.switchTo().defaultContent(); //back to main
         iframe1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));

        driver.switchTo().frame(iframe1);
        wait.withTimeout(Duration.ofSeconds(60));

         divMare = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]")));

        // Verifică dacă există o listă <ul> în acest div
         existaUl = divMare.findElements(By.tagName("ul")).size() > 0;

        // Afișează rezultatul
        if (existaUl) {
            System.out.println("and coresponding rooms!");
        } else {
            Assert.fail("but not coresponding rooms!");
        }


    }

    @Test
    public void verifyClearButton() {


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/rooms");
        System.out.println("Page is loaded successfully");


        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));
        driver.switchTo().frame(iframe);

        WebElement searchWidget = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"hotel-container\"]/section/div/div")));


        if (searchWidget.isDisplayed()) {
            System.out.println("The search widget is displayed");
        } else {
            Assert.fail("The search widget is not displayed");
        }

        driver.findElement(By.id("check-in")).click();

        WebElement checkinFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("calendar")));

        if (checkinFrame.isDisplayed()) {
            System.out.println("The check In Calendar is displayed");
        } else {
            Assert.fail("The check In Calendar is not displayed");
        }

        LocalDate today = LocalDate.now();
        LocalDate threeDaysAfter = today.plusDays(3);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);

        String formattedDate = today.format(formatter);

        String xpath = String.format("//button[@aria-label='%s']", formattedDate);
        System.out.println(xpath);

        WebElement dateButtonCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        wait.withTimeout(Duration.ofSeconds(60));
        dateButtonCheckIn.click();

        WebElement checkinvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_in-value")));
        String formattedDate1 = today.format(newFormatter);

        Assert.assertEquals(checkinvalue.getText(), formattedDate1,"The selected date is NOT displayed in Check In box");
        System.out.println("The selected date is displayed in Check In box");

        wait.withTimeout(Duration.ofSeconds(30));
        WebElement checkOutFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[2]/div[2]/div")));

        if (checkOutFrame.isDisplayed()) {
            System.out.println("The check out Calendar is displayed");
        } else {
            Assert.fail("The check out Calendar is not displayed");
        }
        String newFormattedDate = threeDaysAfter.format(formatter);

        String newXPath = String.format("(//button[@aria-label='%s'])[2]", newFormattedDate);
        System.out.println(newXPath);

        WebElement dateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newXPath)));
        wait.withTimeout(Duration.ofSeconds(60));
        dateButton.click();

        WebElement checkOutvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_out-value")));
        String formattedDate2 = threeDaysAfter.format(newFormatter);

        Assert.assertEquals(checkOutvalue.getText(), formattedDate2,"The selected date is NOT displayed in Check Out box");
        System.out.println("The selected date is displayed in Check Out box");
//////////////////////////////////////////

        WebElement counterInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .value")));
//        WebElement counterInput = driver.findElement(By.cssSelector("#adults .value"));
        WebElement decrementButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .down")));
//        WebElement decrementButton = driver.findElement(By.cssSelector("#adults .down"));
        WebElement incrementButton = driver.findElement(By.cssSelector("#adults .up"));
        int counterValue = Integer.parseInt(counterInput.getText());

        if (counterValue == 1) {
            System.out.println("The value of adults counter is 1");
//            incrementButton.click();
//            int counterValue1 = Integer.parseInt(counterInput.getText());
//            if (counterValue1 == 2) {
//
//                System.out.println("The counter value is increases from 1 to 2");
//
//            } else
//            {
//                Assert.fail("The counter value is not increases from 1 to 2");
//            }

        } else
        {
            Assert.fail("The counter value is not 1");
        }

        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("s-button")));
        searchButton.click();

        String currentUrl = driver.getCurrentUrl();
        if(currentUrl.contains("https://ancabota09.wixsite.com/intern/rooms")) {
            System.out.println("The rooms page is loaded");
        }
        else {
            Assert.fail( "Bad redirect");
        }

        driver.switchTo().defaultContent(); //back to main

//        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));

        driver.switchTo().frame(iframe);
        wait.withTimeout(Duration.ofSeconds(60));

        WebElement checkinvalue1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check-in")));
        WebElement checkoutvalue1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check-out")));

        // Verifică dacă datele coincid
        if (Objects.equals(checkOutvalue.getText(), checkoutvalue1.getText()) && Objects.equals(checkinvalue.getText(), checkinvalue1.getText())) {
            System.out.println("with selected data");
        } else {
            System.out.println("not coresponding data...");
            System.out.println("Check-in extras: " + checkinvalue1.getText() + " vs Check-in așteptat: " + checkinvalue.getText());
            System.out.println("Check-out extras: " + checkoutvalue1.getText() + " vs Check-out așteptat: " + checkOutvalue.getText());
        }

//        driver.switchTo().frame(iframe);

        driver.switchTo().defaultContent(); //back to main
        WebElement iframe1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));

        driver.switchTo().frame(iframe1);
        wait.withTimeout(Duration.ofSeconds(60));

        WebElement divMare = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]")));

        // Verifică dacă există o listă <ul> în acest div
        boolean existaUl = divMare.findElements(By.tagName("ul")).size() > 0;

        // Afișează rezultatul
        if (existaUl) {
            System.out.println("and coresponding rooms!");
        } else {
            Assert.fail("but not coresponding rooms!");
        }


        //99999999999999999999999999   aceasi secventa de mai sus dar cu search again
        WebElement clear = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[1]/h2/a")));


        if (clear.isDisplayed()) {
            System.out.println("The clear button is displayed");
        } else {
            Assert.fail("The clear button is not displayed");
        }

        clear.click();
        WebElement filter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[1]/h2")));
        if (Objects.equals(filter.getText(), "Our Rooms")) {
            System.out.println("The filter set on the result is deleted");
        } else {
            Assert.fail("The filter set on the result is NOT deleted");
        }

        driver.switchTo().defaultContent(); //back to main
        iframe1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));

        driver.switchTo().frame(iframe1);
        wait.withTimeout(Duration.ofSeconds(60));

        divMare = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]")));

        // Verifică dacă există o listă <ul> în acest div
        existaUl = divMare.findElements(By.tagName("ul")).size() > 0;

        // Afișează rezultatul
        if (existaUl) {
            System.out.println("and list with all rooms is displayed!");
        } else {
            Assert.fail("but list with all rooms is NOT displayed!");
        }

    }


    @Test
    public void verifyStandardSuiteMoreInfoButtonPicture() {// noua nu ne trebe de pe poza ne trebe doar din dreapta

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/rooms");
        System.out.println("Page is loaded successfully");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'i6klgqap_0\']/iframe")));
        //WebElement iframe = driver.findElement(By.xpath("//*[@id=\'i6klgqap_0\']/iframe"));
        driver.switchTo().frame(iframe);
        WebElement standardSuiteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'content\']/div/div[2]/div/ul/li[1]/div/div[2]/div[4]/button")));
        Assert.assertTrue(standardSuiteButton.isDisplayed(), "The 'Standard Suite' link is not displayed!");
        standardSuiteButton.click();

        wait.until(ExpectedConditions.urlToBe("https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4"));
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4";
        Assert.assertEquals(currentUrl, expectedUrl, "The URL is incorrect after clicking the Standard button!");

    }


}


