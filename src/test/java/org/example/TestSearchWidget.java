//
package org.example;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static java.awt.SystemColor.text;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
//
//public class TestSearchWidget {
//
//    private WebDriver driver;
//
////    @BeforeClass
////    public void beforeClass() {
//////        driver = new ChromeDriver();
////        ChromeOptions options = new ChromeOptions();
////        options.addArguments("--headless");
////        options.addArguments("--disable-gpu");
////        options.addArguments("--no-sandbox");
////        options.addArguments("--disable-dev-shm-usage");
////        driver = new ChromeDriver(options);
////    }
////    @AfterClass
////    public void afterClass() {
////        driver.quit();
////    }
//@BeforeMethod
//public void setUp() {
//    ChromeOptions options = new ChromeOptions();
//    options.addArguments("--headless",  "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
//    driver = new ChromeDriver(options);
//}
//
//    @AfterMethod
//    public void tearDown() {
//        driver.quit();
//    }
//
//    @Test
//    public void verifyMinimumBoundary() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        System.out.println("Loaded successfully");
//
//        driver.get("https://ancabota09.wixsite.com/intern");
//        System.out.println("Page is loaded successfully");
//
//
//        // Step 3: Validate that the search widget is displayed
//        WebElement searchWidget = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
//        if (searchWidget.isDisplayed()) {
//            System.out.println("The search widget is displayed");
//        } else {
//            Assert.fail("The search widget is not displayed");
//        }
//
//        // Step 4: Validate that the counter does not allow the value to fall below 1
//
//        driver.switchTo().frame(searchWidget);
//        WebElement counterInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .value")));
////        WebElement counterInput =wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .value"));
//        WebElement decrementButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .down")));
////        WebElement decrementButton =wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .down"));
//        WebElement incrementButton =wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .up")));
//
//        int counterValue = Integer.parseInt(counterInput.getText());
//        boolean isDecrementButtonDisabled = false;
//        if (counterValue == 1) {
//            isDecrementButtonDisabled = decrementButton.getAttribute("disabled") != null;
//            System.out.println("The value of adults counter is 1");
//        }
//        else
//            if (counterValue < 1) {
//                Assert.fail("The counter value is below 1");
//            }
//            else {
//            // Attempt to decrement the counter and check if it becomes disabled at value 1
//            while (counterValue > 1) {
//                decrementButton.click();
//                counterValue = Integer.parseInt(counterInput.getText());
//                System.out.println("Counter value after decrement: " + counterValue);
//                if (counterValue == 1) {
//                    // Check if the decrement button is disabled
//                    isDecrementButtonDisabled = decrementButton.getAttribute("disabled")  != null;
//                    break;
//                }
//            }
//        }
//
//        if (isDecrementButtonDisabled) {
//            System.out.println("The decrement button is disabled at counter value 1");
//        } else {
//            Assert.fail("The decrement button is not disabled at counter value 1");
//        }
//    }


public class TestSearchWidget {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1280, 800)); // exemplu rezoluție

    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Check if the test failed
        if (!result.isSuccess()) {
            TestUtils.captureScreenshot(driver, result.getName());
        }
        driver.quit();
    }

    @Test
    public void verifyMinimumBoundary() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern");
        System.out.println("Page is loaded successfully");

        // Step 3: Validate that the search widget is displayed
        WebElement searchWidget = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        if (searchWidget.isDisplayed()) {
            System.out.println("The search widget is displayed");
        } else {
            throw new AssertionError("The search widget is not displayed");
        }

        // Step 4: Validate that the counter does not allow the value to fall below 1

        driver.switchTo().frame(searchWidget);
        WebElement counterInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .value")));
        WebElement decrementButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .down")));

        int counterValue = Integer.parseInt(counterInput.getText());
        boolean isDecrementButtonDisabled = false;

        if (counterValue == 1) {
            isDecrementButtonDisabled = decrementButton.getAttribute("disabled") != null;
            System.out.println("The value of adults counter is 1");
        } else if (counterValue < 1) {
            throw new AssertionError("The counter value is below 1");
        } else {
            while (counterValue > 1) {
                decrementButton.click();
                counterValue = Integer.parseInt(counterInput.getText());
                System.out.println("Counter value after decrement: " + counterValue);
                if (counterValue == 1) {
                    isDecrementButtonDisabled = decrementButton.getAttribute("disabled") != null;
                    break;
                }
            }
        }

        if (isDecrementButtonDisabled) {
            System.out.println("The decrement button is disabled at counter value 1");
        } else {
            throw new AssertionError("The decrement button is not disabled at counter value 1");
        }
    }


    public static void captureScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String screenshotName = testName + "_" + timestamp + ".png";
        String screenshotPath = "screenshots/" + screenshotName;

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(screenshot, new File(screenshotPath));
            System.out.println("Screenshot saved: " + screenshotPath);
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }
    }



    @Test
    public void CheckOutOneDayAfterCheckIn() {


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern");
        System.out.println("Page is loaded successfully");
        WebElement searchWidget = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));


        if (searchWidget.isDisplayed()) {
            System.out.println("The search widget is displayed");
        } else {
            Assert.fail("The search widget is not displayed");
        }

        driver.switchTo().frame(searchWidget);

        driver.findElement(By.id("check-in")).click();

        driver.switchTo().defaultContent(); //back to main
        WebElement checkinFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));

        if (checkinFrame.isDisplayed()) {
            System.out.println("The check In Calendar is displayed");
        } else {
            Assert.fail("The check In Calendar is not displayed");
        }

        driver.switchTo().frame(checkinFrame);

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);

        String formattedDate = today.format(formatter);

        String xpath = String.format("//button[@aria-label='%s']", formattedDate);
        System.out.println(xpath);

//        WebElement dateButtonCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
////        wait.withTimeout(Duration.ofSeconds(30));
//        dateButtonCheckIn.click();
        for (int attempts = 0; attempts < 3; attempts++) {
            try {
                WebElement dateButtonCheckIn = driver.findElement(By.xpath(xpath));
                dateButtonCheckIn.click();
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Retrying to click on the calendar date...");
            }
        }

        /*driver.switchTo().frame(searchWidget);
        checkOutField.click();*/

        driver.switchTo().defaultContent(); //back to main


        driver.switchTo().frame(searchWidget);
        WebElement checkinvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check-in-value")));
        String formattedDate1 = today.format(newFormatter);

        Assert.assertEquals(checkinvalue.getText(), formattedDate1,"The selected date is NOT displayed in Check In box");
        System.out.println("The selected date is displayed in Check In box");


        driver.switchTo().defaultContent(); //back to main

        WebElement checkOutFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        driver.switchTo().frame(checkOutFrame);

        String newFormattedDate = tomorrow.format(formatter);

        String newXPath = String.format("//button[@aria-label='%s']", newFormattedDate);
//
//        WebElement dateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newXPath)));
//        wait.withTimeout(Duration.ofSeconds(30));
//        dateButton.click();
        for (int attempts = 0; attempts < 3; attempts++) {
            try {
                WebElement dateButton = driver.findElement(By.xpath(newXPath));
                dateButton.click();
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Retrying to click on the calendar date...");
            }
        }


        driver.switchTo().defaultContent(); //back to main

        driver.switchTo().frame(searchWidget);
        WebElement checkOutvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check-out-value")));
        String formattedDate2 = tomorrow.format(newFormatter);

        Assert.assertEquals(checkOutvalue.getText(), formattedDate2,"The selected date is NOT displayed in Check Out box");
        System.out.println("The selected date is displayed in Check Out box");

    }



    @Test
    public void SearchWithValidNumberOfAdults() throws InterruptedException {


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern");
        System.out.println("Page is loaded successfully");


        WebElement searchWidget = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        // i frame       search widget id : i6kppi75


        if (searchWidget.isDisplayed()) {
            System.out.println("The search widget is displayed");
        } else {
            Assert.fail("The search widget is not displayed");
        }

        driver.switchTo().frame(searchWidget);

        driver.findElement(By.id("check-in")).click();

        driver.switchTo().defaultContent(); //back to main
        WebElement checkinFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));

        if (checkinFrame.isDisplayed()) {
            System.out.println("The check In Calendar is displayed");
        } else {
            Assert.fail("The check In Calendar is not displayed");
        }

        driver.switchTo().frame(checkinFrame);

        LocalDate today = LocalDate.now();
        LocalDate threeDaysAfter = today.plusDays(3);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);

        String formattedDate = today.format(formatter);

        String xpath = String.format("//button[@aria-label='%s']", formattedDate);
        System.out.println(xpath);

//        WebElement dateButtonCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
//        wait.withTimeout(Duration.ofSeconds(60));
//        dateButtonCheckIn.click();
        for (int attempts = 0; attempts < 3; attempts++) {
            try {
                WebElement dateButtonCheckIn = driver.findElement(By.xpath(xpath));
                dateButtonCheckIn.click();
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Retrying to click on the calendar date...");
            }
        }
        /*driver.switchTo().frame(searchWidget);
        checkOutField.click();*/

        driver.switchTo().defaultContent(); //back to main


        driver.switchTo().frame(searchWidget);
        WebElement checkinvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check-in-value")));
        String formattedDate1 = today.format(newFormatter);

        Assert.assertEquals(checkinvalue.getText(), formattedDate1,"The selected date is NOT displayed in Check In box");
        System.out.println("The selected date is displayed in Check In box");


        driver.switchTo().defaultContent(); //back to main

        WebElement checkOutFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));

        if (checkOutFrame.isDisplayed()) {
            System.out.println("The check out Calendar is displayed");
        } else {
            Assert.fail("The check out Calendar is not displayed");
        }

        driver.switchTo().frame(checkOutFrame);

        String newFormattedDate = threeDaysAfter.format(formatter);

        String newXPath = String.format("//button[@aria-label='%s']", newFormattedDate);

//        WebElement dateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newXPath)));
//        wait.withTimeout(Duration.ofSeconds(30));
//        dateButton.click();
        for (int attempts = 0; attempts < 3; attempts++) {
            try {
                WebElement dateButton = driver.findElement(By.xpath(newXPath));
                dateButton.click();
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Retrying to click on the calendar date...");
            }
        }
        driver.switchTo().defaultContent(); //back to main

        driver.switchTo().frame(searchWidget);
        WebElement checkOutvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check-out-value")));
        String formattedDate2 = threeDaysAfter.format(newFormatter);

        Assert.assertEquals(checkOutvalue.getText(), formattedDate2,"The selected date is NOT displayed in Check Out box");
        System.out.println("The selected date is displayed in Check Out box");
//////////////////////////////////////////
        // Step 4: Increment the counter step by step to 6
        WebElement counterInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .value")));
        WebElement incrementButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#adults .up")));

        int counterValue = Integer.parseInt(counterInput.getText());
        int incercari=3;
        while (counterValue < 2) {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", incrementButton);

                incrementButton.click();
                Thread.sleep(500); // Așteaptă puțin pentru ca interfața să actualizeze valoarea

                counterInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .value")));
                counterValue = Integer.parseInt(counterInput.getText());

                System.out.println("Counter value after increment: " + counterValue);
            } catch (ElementClickInterceptedException e) {
                System.out.println("Click intercepted, attempting to scroll to the button and retry...");
                incercari-=1;
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", incrementButton);
                if(incercari==0)
                    break;
            }
        }

        // Validate that the counter value is 2
        if (counterValue == 2) {
            System.out.println("Counter value is correctly set to 2.");
        } else {
            Assert.fail("Failed to reach counter value 2. Current value: " + counterValue);
        }

        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("s-button")));
        searchButton.click();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String currentUrl = driver.getCurrentUrl();
        if(currentUrl.contains("https://ancabota09.wixsite.com/intern/rooms")) {
            System.out.println("The rooms page is loaded");
        }
        else {
            System.out.println(currentUrl);

            Assert.fail( "Bad redirect");
        }

        driver.switchTo().defaultContent(); //back to main

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));

        driver.switchTo().frame(iframe);
        wait.withTimeout(Duration.ofSeconds(60));
        System.out.println("in iframe");

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

    }


    @Test
    public void SearchWithInvalidNumberOfAdults() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern");
        System.out.println("Page is loaded successfully");


        WebElement searchWidget = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        // i frame       search widget id : i6kppi75


        if (searchWidget.isDisplayed()) {
            System.out.println("The search widget is displayed");
        } else {
            Assert.fail("The search widget is not displayed");
        }

        driver.switchTo().frame(searchWidget);

        driver.findElement(By.id("check-in")).click();

        driver.switchTo().defaultContent(); //back to main
        WebElement checkinFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));

        if (checkinFrame.isDisplayed()) {
            System.out.println("The check In Calendar is displayed");
        } else {
            Assert.fail("The check In Calendar is not displayed");
        }

        driver.switchTo().frame(checkinFrame);

        LocalDate today = LocalDate.now();
        LocalDate threeDaysAfter = today.plusDays(3);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);

        String formattedDate = today.format(formatter);

        String xpath = String.format("//button[@aria-label='%s']", formattedDate);
        System.out.println(xpath);

//        WebElement dateButtonCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
//        wait.withTimeout(Duration.ofSeconds(80));
//        dateButtonCheckIn.click();
        for (int attempts = 0; attempts < 3; attempts++) {
            try {
                WebElement dateButtonCheckIn = driver.findElement(By.xpath(xpath));
                dateButtonCheckIn.click();
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Retrying to click on the calendar date...");
            }
        }
        /*driver.switchTo().frame(searchWidget);
        checkOutField.click();*/

        driver.switchTo().defaultContent(); //back to main


        driver.switchTo().frame(searchWidget);
        WebElement checkinvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check-in-value")));
        String formattedDate1 = today.format(newFormatter);

        Assert.assertEquals(checkinvalue.getText(), formattedDate1,"The selected date is NOT displayed in Check In box");
        System.out.println("The selected date is displayed in Check In box");


        driver.switchTo().defaultContent(); //back to main

        WebElement checkOutFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));

        if (checkOutFrame.isDisplayed()) {
            System.out.println("The check out Calendar is displayed");
        } else {
            Assert.fail("The check out Calendar is not displayed");
        }

        driver.switchTo().frame(checkOutFrame);

        String newFormattedDate = threeDaysAfter.format(formatter);

        String newXPath = String.format("//button[@aria-label='%s']", newFormattedDate);

//        WebElement dateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newXPath)));
//        wait.withTimeout(Duration.ofSeconds(30));
//        dateButton.click();
        for (int attempts = 0; attempts < 3; attempts++) {
            try {
                WebElement dateButton = driver.findElement(By.xpath(newXPath));
                dateButton.click();
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Retrying to click on the calendar date...");
            }
        }
        driver.switchTo().defaultContent(); //back to main

        driver.switchTo().frame(searchWidget);
        WebElement checkOutvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check-out-value")));
        String formattedDate2 = threeDaysAfter.format(newFormatter);

        Assert.assertEquals(checkOutvalue.getText(), formattedDate2,"The selected date is NOT displayed in Check Out box");
        System.out.println("The selected date is displayed in Check Out box");
//////////////////////////////////////////

        WebElement counterInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .value")));
        int counterValue = Integer.parseInt(counterInput.getText());
        if (counterValue == 1) {
        while (counterValue < 7) {
            try {
                // Localizează butonul "increment" din nou pentru a evita erorile de tip StaleElementReferenceException
                WebElement incrementButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#adults .up")));
                incrementButton.click();

                // Așteaptă puțin pentru ca interfața să actualizeze valoarea
                Thread.sleep(500); // Îl poți înlocui cu o așteptare explicită dacă este nevoie

                // Actualizează valoarea counterului după fiecare click
                counterInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .value")));
                counterValue = Integer.parseInt(counterInput.getText());

                System.out.println("Counter value after increment: " + counterValue);



            } catch (StaleElementReferenceException e) {
                // Capturăm eroarea de StaleElementReferenceException și încercăm să regăsim elementul
                System.out.println("Elementul a devenit stale, încerc să-l regăsesc...");
            } catch (Exception e) {
                // Capturăm alte erori care ar putea apărea
                System.out.println("A apărut o eroare: " + e.getMessage());
                break; // Iese din buclă în caz de eroare
            }
        }


    } else
    {
        Assert.fail("The counter value is not 1");
    }
        if(counterValue==7){
        System.out.println("The counter value is increases from 1 to 7");

    } else
    {
        Assert.fail("The counter value is not increases from 1 to 7");
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

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));

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
    }


    @Test
    public void SearchWith2Kids() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern");
        System.out.println("Page is loaded successfully");


        WebElement searchWidget = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        // i frame       search widget id : i6kppi75


        if (searchWidget.isDisplayed()) {
            System.out.println("The search widget is displayed");
        } else {
            Assert.fail("The search widget is not displayed");
        }

        driver.switchTo().frame(searchWidget);

        driver.findElement(By.id("check-in")).click();

        driver.switchTo().defaultContent(); //back to main
        WebElement checkinFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));

        if (checkinFrame.isDisplayed()) {
            System.out.println("The check In Calendar is displayed");
        } else {
            Assert.fail("The check In Calendar is not displayed");
        }

        driver.switchTo().frame(checkinFrame);

        LocalDate today = LocalDate.now();
        LocalDate threeDaysAfter = today.plusDays(3);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);

        String formattedDate = today.format(formatter);

        String xpath = String.format("//button[@aria-label='%s']", formattedDate);
        System.out.println(xpath);

//        WebElement dateButtonCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
//        wait.withTimeout(Duration.ofSeconds(30));
//        dateButtonCheckIn.click();
        for (int attempts = 0; attempts < 3; attempts++) {
            try {
                WebElement dateButtonCheckIn = driver.findElement(By.xpath(xpath));
                dateButtonCheckIn.click();
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Retrying to click on the calendar date...");
            }
        }
        /*driver.switchTo().frame(searchWidget);
        checkOutField.click();*/

        driver.switchTo().defaultContent(); //back to main


        driver.switchTo().frame(searchWidget);
        WebElement checkinvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check-in-value")));
        String formattedDate1 = today.format(newFormatter);

        Assert.assertEquals(checkinvalue.getText(), formattedDate1,"The selected date is NOT displayed in Check In box");
        System.out.println("The selected date is displayed in Check In box");


        driver.switchTo().defaultContent(); //back to main

        WebElement checkOutFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        driver.switchTo().frame(checkOutFrame);

        String newFormattedDate = threeDaysAfter.format(formatter);

        String newXPath = String.format("//button[@aria-label='%s']", newFormattedDate);

//        WebElement dateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newXPath)));
////        wait.withTimeout(Duration.ofSeconds(30));
//        dateButton.click();
        for (int attempts = 0; attempts < 3; attempts++) {
            try {
                WebElement dateButton = driver.findElement(By.xpath(newXPath));
                dateButton.click();
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Retrying to click on the calendar date...");
            }
        }
        driver.switchTo().defaultContent(); //back to main

        driver.switchTo().frame(searchWidget);
        WebElement checkOutvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check-out-value")));
        String formattedDate2 = threeDaysAfter.format(newFormatter);

        Assert.assertEquals(checkOutvalue.getText(), formattedDate2,"The selected date is NOT displayed in Check Out box");
        System.out.println("The selected date is displayed in Check Out box");


        WebElement counterInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .value")));
        WebElement incrementButton =wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .up")));
        int counterValue = Integer.parseInt(counterInput.getText());

        if (counterValue == 1) {
            System.out.println("The value of adults counter is 1");

        } else
        {
            Assert.fail("The counter value is not 1");
        }
        WebElement counterInputk = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#children .value")));
        WebElement incrementButtonk =wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#children .up")));
        int counterValuek = Integer.parseInt(counterInputk.getText());
        if (counterValuek == 0) {
            while (counterValuek < 2) {
                try {
                    // Localizează butonul "increment" din nou pentru a evita erorile de tip StaleElementReferenceException
                    incrementButtonk = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#children .up")));
                    incrementButtonk.click();

                    // Așteaptă puțin pentru ca interfața să actualizeze valoarea
                    Thread.sleep(500); // Îl poți înlocui cu o așteptare explicită dacă este nevoie

                    // Actualizează valoarea counterului după fiecare click
                    counterInputk = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#children .value")));
                    counterValuek = Integer.parseInt(counterInputk.getText());

                    System.out.println("Counter value after increment: " + counterValuek);



                } catch (StaleElementReferenceException e) {
                    // Capturăm eroarea de StaleElementReferenceException și încercăm să regăsim elementul
                    System.out.println("Elementul a devenit stale, încerc să-l regăsesc...");
                } catch (Exception e) {
                    // Capturăm alte erori care ar putea apărea
                    System.out.println("A apărut o eroare: " + e.getMessage());
                    break; // Iese din buclă în caz de eroare
                }
            }


        } else
        {
            Assert.fail("The counter value is not 0");
        }
        if(counterValuek==2){
            System.out.println("The counter value is increases from 0 to 2");

        } else
        {
            Assert.fail("The counter value is not increases from 0 to 2");
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

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));

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

    }

}