
package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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

public class TestSearchWidget {

    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
    }

    @AfterClass
    public void afterClass() {
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
            Assert.fail("The search widget is not displayed");
        }

        // Step 4: Validate that the counter does not allow the value to fall below 1

        driver.switchTo().frame(searchWidget);
        WebElement counterInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .value")));
//        WebElement counterInput = driver.findElement(By.cssSelector("#adults .value"));
        WebElement decrementButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .down")));
//        WebElement decrementButton = driver.findElement(By.cssSelector("#adults .down"));
        WebElement incrementButton = driver.findElement(By.cssSelector("#adults .up"));

        int counterValue = Integer.parseInt(counterInput.getText());
        boolean isDecrementButtonDisabled = false;
        if (counterValue == 1) {
            isDecrementButtonDisabled = decrementButton.getAttribute("disabled") != null;
            System.out.println("The value of adults counter is 1");
        }
        else
            if (counterValue < 1) {
                Assert.fail("The counter value is below 1");
            }
            else {
            // Attempt to decrement the counter and check if it becomes disabled at value 1
            while (counterValue > 1) {
                decrementButton.click();
                counterValue = Integer.parseInt(counterInput.getText());
                System.out.println("Counter value after decrement: " + counterValue);
                if (counterValue == 1) {
                    // Check if the decrement button is disabled
                    isDecrementButtonDisabled = decrementButton.getAttribute("disabled")  != null;
                    break;
                }
            }
        }

        if (isDecrementButtonDisabled) {
            System.out.println("The decrement button is disabled at counter value 1");
        } else {
            Assert.fail("The decrement button is not disabled at counter value 1");
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

        WebElement dateButtonCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        wait.withTimeout(Duration.ofSeconds(30));
        dateButtonCheckIn.click();

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

        WebElement dateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newXPath)));
        wait.withTimeout(Duration.ofSeconds(30));
        dateButton.click();

        driver.switchTo().defaultContent(); //back to main

        driver.switchTo().frame(searchWidget);
        WebElement checkOutvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check-out-value")));
        String formattedDate2 = tomorrow.format(newFormatter);

        Assert.assertEquals(checkOutvalue.getText(), formattedDate2,"The selected date is NOT displayed in Check Out box");
        System.out.println("The selected date is displayed in Check Out box");

    }



    @Test
    public void SearchWithValidNumberOfAdults() {


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

        WebElement dateButtonCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        wait.withTimeout(Duration.ofSeconds(60));
        dateButtonCheckIn.click();

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

        WebElement dateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newXPath)));
        wait.withTimeout(Duration.ofSeconds(30));
        dateButton.click();

        driver.switchTo().defaultContent(); //back to main

        driver.switchTo().frame(searchWidget);
        WebElement checkOutvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check-out-value")));
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
            incrementButton.click();
            int counterValue1 = Integer.parseInt(counterInput.getText());
            if (counterValue1 == 2) {

                System.out.println("The counter value is increases from 1 to 2");

            } else
            {
                Assert.fail("The counter value is not increases from 1 to 2");
            }

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

        WebElement dateButtonCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        wait.withTimeout(Duration.ofSeconds(80));
        dateButtonCheckIn.click();

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

        WebElement dateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newXPath)));
        wait.withTimeout(Duration.ofSeconds(30));
        dateButton.click();

        driver.switchTo().defaultContent(); //back to main

        driver.switchTo().frame(searchWidget);
        WebElement checkOutvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check-out-value")));
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
            for(int i=1;i<7;i++)
                incrementButton.click();
            int counterValue1 = Integer.parseInt(counterInput.getText());
            if (counterValue1 == 7) {
                System.out.println("The counter value is increases from 1 to 7");
            } else
            {
                Assert.fail("The counter value is not increases from 1 to 7");
            }

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

        WebElement dateButtonCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        wait.withTimeout(Duration.ofSeconds(30));
        dateButtonCheckIn.click();

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

        WebElement dateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newXPath)));
        wait.withTimeout(Duration.ofSeconds(30));
        dateButton.click();

        driver.switchTo().defaultContent(); //back to main

        driver.switchTo().frame(searchWidget);
        WebElement checkOutvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check-out-value")));
        String formattedDate2 = threeDaysAfter.format(newFormatter);

        Assert.assertEquals(checkOutvalue.getText(), formattedDate2,"The selected date is NOT displayed in Check Out box");
        System.out.println("The selected date is displayed in Check Out box");


        WebElement counterInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .value")));
//        WebElement counterInput = driver.findElement(By.cssSelector("#adults .value"));
        WebElement decrementButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .down")));
//        WebElement decrementButton = driver.findElement(By.cssSelector("#adults .down"));
        WebElement incrementButton = driver.findElement(By.cssSelector("#adults .up"));
        int counterValue = Integer.parseInt(counterInput.getText());

        if (counterValue == 1) {
            System.out.println("The value of adults counter is 1");
            incrementButton.click();
            int counterValue1 = Integer.parseInt(counterInput.getText());
            if (counterValue1 == 2) {

                System.out.println("The counter value is increases from 1 to 2");

            } else
            {
                Assert.fail("The counter value is not increases from 1 to 2");
            }

        } else
        {
            Assert.fail("The counter value is not 1");
        }
        WebElement counterInputk = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#children .value")));
//        WebElement counterInput = driver.findElement(By.cssSelector("#kids .value"));
        WebElement decrementButtonk = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#children .down")));
//        WebElement decrementButton = driver.findElement(By.cssSelector("#kids .down"));
        WebElement incrementButtonk = driver.findElement(By.cssSelector("#children .up"));
        int counterValuek = Integer.parseInt(counterInputk.getText());

        if (counterValuek == 0) {
            System.out.println("The value of kids counter is 0");
            incrementButtonk.click();
            incrementButtonk.click();

            int counterValue1k = Integer.parseInt(counterInputk.getText());
            if (counterValue1k == 2) {

                System.out.println("The counter value is increases from 0 to 2");

            } else
            {
                Assert.fail("The counter value is not increases from 0 to 2");
            }

        } else
        {
            Assert.fail("The counter value is not 0");
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