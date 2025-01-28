package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestStandardSuiteRoomPage {
    private WebDriver driver;

//    @BeforeClass
//    public void beforeClass() {
////        driver = new ChromeDriver();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        options.addArguments("--disable-gpu");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-dev-shm-usage");
//        driver = new ChromeDriver(options);
//    }
//    @AfterClass
//    public void afterClass() {
//        driver.quit();
//    }
    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless",  "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1424, 968)); // Rezoluție mai mică

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void verifyImage() {


        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4");
        System.out.println("Page is loaded successfully");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement iframeStandardSuite = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframeStandardSuite);


        WebElement StandardSuiteImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"singleroom\"]/div[3]/div[1]/div/div[1]/div/img")));
        String src = StandardSuiteImage.getAttribute("src");
        System.out.println("Image src is: " + src);
        if (src != null && !src.equals("https://static.wixstatic.com/media/fde015_cb4dcccb4258499a894623f5282baa98.png/v1/fill/w_649,h_408,q_85,usm_0.66_1.00_0.01/fde015_cb4dcccb4258499a894623f5282baa98.png")) {
            System.out.println("The 'Standard Suite' image is displayed!");

        } else {
            Assert.fail("The 'Standard Suite' image is not displayed!");
        }
    }

    @Test
    public void verifyReadOurPolicies() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4");
        System.out.println("Page is loaded successfully");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        //read our policies
        WebElement iframeStandardSuite = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframeStandardSuite);

        WebElement ReadOurPoliciesButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'singleroom\']/div[3]/div[2]/div[4]/ul/li[2]/span/a")));
        Assert.assertTrue(ReadOurPoliciesButton.isDisplayed(), " Read Our Policies button is NOT displayed\n");
        System.out.println("Read Our Policies button is displayed");


        ReadOurPoliciesButton.click();

        String parentWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();

        // Loop through the window handles to find the handle of the new window
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(parentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        String currentUrl = driver.getCurrentUrl();
        // String expectedUrl="https://hotels.wixapps.net/index.html?pageId=crop&compId=i6klgqap_0&viewerCompId=i6klgqap_0&siteRevision=3&viewMode=site&deviceType=desktop&locale=en&tz=Europe%2FVaduz&regionalLanguage=en&width=980&height=788&instance=bgG5-FLdoyoEVxu4wnuKI-ASbDcw4lh2wE9w6ORxlNM.eyJpbnN0YW5jZUlkIjoiZjgyZWUyNjQtMDI3NC00OTc4LTkwODItNmVmNzE1Yzg0YjQzIiwiYXBwRGVmSWQiOiIxMzVhYWQ4Ni05MTI1LTYwNzQtNzM0Ni0yOWRjNmEzYzliY2YiLCJtZXRhU2l0ZUlkIjoiZDczMzdkMjAtZWE2OS00NDYwLWEzNzUtYTkxNzI2Y2IzYzI2Iiwic2lnbkRhdGUiOiIyMDI0LTA4LTA2VDA3OjAxOjM2Ljg0OVoiLCJkZW1vTW9kZSI6ZmFsc2UsIm9yaWdpbkluc3RhbmNlSWQiOiI5NDk0YmM4Ny1jODE1LTQ5ZjEtYTNlMy1mYWJhNzBiNGJlYzgiLCJhaWQiOiI1OWFkOTkxNi1mNmUxLTQwN2QtYTViMS1jYzJhZGY5M2I2MDkiLCJiaVRva2VuIjoiMmYxZDlmNDQtZTgxZC0wZDE4LTMzZjctYzdlMDMzMDM3NzY1Iiwic2l0ZU93bmVySWQiOiI2NTQ3ZGNlYy1mZjMxLTRjMmItODIxNy1jNzgxNDQ3ZTExYjkifQ&currency=USD&currentCurrency=USD&commonConfig=%7B%22brand%22%3A%22wix%22%2C%22host%22%3A%22VIEWER%22%2C%22bsi%22%3A%223a1595d6-24cb-46c6-ac93-60a750abc9ce%7C1%22%2C%22siteRevision%22%3A%223%22%2C%22BSI%22%3A%223a1595d6-24cb-46c6-ac93-60a750abc9ce%7C1%22%7D&currentRoute=.%2Frooms&target=_top&section-url=https%3A%2F%2Fancabota09.wixsite.com%2Fintern%2Frooms%2F&vsi=ab7e91e0-0c39-46c9-8fc9-3921fea788a8&userLanguage=en#/terms/afda6ba1-efd1-4432-bd42-dd678bd4beb4";
        Assert.assertTrue(currentUrl.contains("https://hotels.wixapps.net/index.html?pageId=crop&compId=i6klgqap_0&viewerCompId=i6klgqap_0"), "The URL is incorrect after clicking the button!");
        System.out.println("Policies page is loaded");

        WebElement CheckInTimetext = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'terms\']/div/div[1]/span[1]/label/span")));
        Assert.assertTrue(CheckInTimetext.isDisplayed(), "The 'Check In time' is not displayed!");

        WebElement CheckOutTimetext = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'terms\']/div/div[1]/span[3]/label/span")));
        Assert.assertTrue(CheckOutTimetext.isDisplayed(), "The 'Check Out time' is not displayed!");

        driver.switchTo().window(parentWindowHandle);
    }


    @Test
    public void verifyMinimumBoundary() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4");
        System.out.println("Page is loaded successfully");

        WebElement iframeStandardSuite = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframeStandardSuite);

        // Step 3: Validate that the search widget is displayed
        WebElement searchWidget = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("search")));
        if (searchWidget.isDisplayed()) {
            System.out.println("The search widget is displayed");
        } else {
            Assert.fail("The search widget is not displayed");
        }

        // Step 4: Validate that the counter does not allow the value to fall below 1

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
        } else if (counterValue < 1) {
            Assert.fail("The counter value is below 1");
        } else {
            // Attempt to decrement the counter and check if it becomes disabled at value 1
            while (counterValue > 1) {
                decrementButton.click();
                counterValue = Integer.parseInt(counterInput.getAttribute("value"));
                System.out.println("Counter value after decrement: " + counterValue);

                if (counterValue == 1) {
                    // Check if the decrement button is disabled
                    isDecrementButtonDisabled = decrementButton.getAttribute("disabled") != null;
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
    public void verifyMaximumBoundary() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4");
        System.out.println("Page is loaded successfully");

        // Scroll to iframe to ensure visibility
        WebElement iframeStandardSuite = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", iframeStandardSuite);

        driver.switchTo().frame(iframeStandardSuite);

        // Step 3: Validate that the search widget is displayed
        WebElement searchWidget = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("search")));
        if (searchWidget.isDisplayed()) {
            System.out.println("The search widget is displayed");
        } else {
            Assert.fail("The search widget is not displayed");
        }

        // Step 4: Increment the counter step by step to 6
        WebElement counterInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .value")));
        WebElement incrementButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#adults .up")));

        int counterValue = Integer.parseInt(counterInput.getText());

        while (counterValue < 6) {
            try {
                incrementButton.click();
                Thread.sleep(500); // Așteaptă puțin pentru ca interfața să actualizeze valoarea

                counterInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .value")));
                counterValue = Integer.parseInt(counterInput.getText());

                System.out.println("Counter value after increment: " + counterValue);
            } catch (ElementClickInterceptedException e) {
                System.out.println("Click intercepted, attempting to scroll to the button and retry...");
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", incrementButton);
            }
        }

        // Validate that the counter value is 6
        if (counterValue == 6) {
            System.out.println("Counter value is correctly set to 6.");
        } else {
            Assert.fail("Failed to reach counter value 6. Current value: " + counterValue);
        }

        // Validate that the increment button is disabled
        boolean isIncrementButtonDisabled = incrementButton.getAttribute("disabled") != null;
        if (isIncrementButtonDisabled) {
            System.out.println("The increment button is disabled at counter value 6");
        } else {
            Assert.fail("The increment button is not disabled at counter value 6");
        }
    }

    @Test
    public void CheckOutOneDayAfterCheckIn() {


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4");
        System.out.println("Page is loaded successfully");


        WebElement searchWidget = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));

        driver.switchTo().frame(searchWidget);
        // Step 3: Validate that the search widget is displayed
        WebElement searchWidget1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("search")));
        if (searchWidget1.isDisplayed()) {
            System.out.println("The search widget is displayed");
        } else {
            Assert.fail("The search widget is not displayed");
        }

        driver.findElement(By.id("check-in")).click();

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);

        String formattedDate = today.format(formatter);

        String xpath = String.format("//button[@aria-label=\"%s\"]", formattedDate);
        System.out.println(xpath);

        WebElement dateButtonCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        wait.withTimeout(Duration.ofSeconds(30));
        dateButtonCheckIn.click();

        WebElement checkinvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_in-value")));
        String formattedDate1 = today.format(newFormatter);

        Assert.assertEquals(checkinvalue.getText(), formattedDate1, "The selected date is NOT displayed in Check In box");
        System.out.println("The selected date is displayed in Check In box");

        String newFormattedDate = tomorrow.format(formatter);

        String newXPath = String.format("(//button[@aria-label='%s'])[2]", newFormattedDate);

        WebElement dateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newXPath)));
        wait.withTimeout(Duration.ofSeconds(30));
        dateButton.click();

        WebElement checkOutvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check-out")));
        String formattedDate2 = tomorrow.format(newFormatter);

        Assert.assertEquals(checkOutvalue.getText(), formattedDate2, "The selected date is NOT displayed in Check Out box");
        System.out.println("The selected date is displayed in Check Out box");

    }


    @Test
    public void verifyOneNightPriceValue() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4");
        System.out.println("Page is loaded successfully");


        WebElement searchWidget = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));

        driver.switchTo().frame(searchWidget);
        // Step 3: Validate that the search widget is displayed
        WebElement searchWidget1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("search")));
        if (searchWidget1.isDisplayed()) {
            System.out.println("The search widget is displayed");
        } else {
            Assert.fail("The search widget is not displayed");
        }

        driver.findElement(By.id("check-in")).click();

        LocalDate today = LocalDate.now();
        LocalDate after3days = today.plusDays(4);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);

        String formattedDate = today.format(formatter);

        String xpath = String.format("//button[@aria-label=\"%s\"]", formattedDate);
        System.out.println(xpath);

        WebElement dateButtonCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        wait.withTimeout(Duration.ofSeconds(30));
        dateButtonCheckIn.click();

        WebElement checkinvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_in-value")));
        String formattedDate1 = today.format(newFormatter);

        Assert.assertEquals(checkinvalue.getText(), formattedDate1, "The selected date is NOT displayed in Check In box");
        System.out.println("The selected date is displayed in Check In box");

        String newFormattedDate = after3days.format(formatter);

        String newXPath = String.format("(//button[@aria-label='%s'])[2]", newFormattedDate);
        System.out.println(newXPath);


        WebElement dateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newXPath)));
        wait.withTimeout(Duration.ofSeconds(30));
        dateButton.click();

        WebElement checkOutvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_out-value")));
        String formattedDate2 = after3days.format(newFormatter);

        Assert.assertEquals(checkOutvalue.getText(), formattedDate2, "The selected date is NOT displayed in Check Out box");
        System.out.println("The selected date is displayed in Check Out box");


        //////
        WebElement counterInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .value")));
//        WebElement counterInput = driver.findElement(By.cssSelector("#adults .value"));
        WebElement decrementButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .down")));
//        WebElement decrementButton = driver.findElement(By.cssSelector("#adults .down"));
        WebElement incrementButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .up")));
        int counterValue = Integer.parseInt(counterInput.getText());

        boolean isIncrementButtonDisabled = false;
        if (counterValue < 1) {
            Assert.fail("The counter value is below 1");
        }
        isIncrementButtonDisabled = incrementButton.getAttribute("disabled") != null;
        if (isIncrementButtonDisabled) {
            Assert.fail("The increment button is disabled at counter value 1");

        } else {
            System.out.println("The increment button is not disabled at counter value 1");
        }
        // Attempt to INCREMENT
        while (counterValue < 2) {
            incrementButton.click();
            counterValue = Integer.parseInt(counterInput.getText());
            System.out.println("Counter value after increment: " + counterValue);

        }
////
//        try {
//            Thread.sleep(1005000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        WebElement price1night = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("price")));
        String price1nightValue = price1night.getText();
        List<WebElement> nightRows = driver.findElements(By.cssSelector("tr.nights"));

        for (WebElement row : nightRows) {
            String nights = row.findElement(By.cssSelector("span[ng-bind='nights']")).getText();
            String price = row.findElement(By.cssSelector("td[ng-bind^='paymentDetails.roomSubtotal']")).getText();

            System.out.println("Nights: " + nights + ", Price actual: " + price + " price per night: " + price1nightValue);
            Assert.assertEquals(price, price1nightValue, "The value of the first displayed price in reservation details is not for one night");

            System.out.println("The value of the first displayed price in reservation details is for one night");
        }


    }

    @Test
    public void verifyTotalPriceValue() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4");
        System.out.println("Page is loaded successfully");


        WebElement searchWidget = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));

        driver.switchTo().frame(searchWidget);
        // Step 3: Validate that the search widget is displayed
        WebElement searchWidget1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("search")));
        if (searchWidget1.isDisplayed()) {
            System.out.println("The search widget is displayed");
        } else {
            Assert.fail("The search widget is not displayed");
        }

        driver.findElement(By.id("check-in")).click();

        LocalDate today = LocalDate.now();
        LocalDate after3days = today.plusDays(4);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);

        String formattedDate = today.format(formatter);

        String xpath = String.format("//button[@aria-label=\"%s\"]", formattedDate);
        System.out.println(xpath);

        WebElement dateButtonCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        wait.withTimeout(Duration.ofSeconds(30));
        dateButtonCheckIn.click();

        WebElement checkinvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_in-value")));
        String formattedDate1 = today.format(newFormatter);

        Assert.assertEquals(checkinvalue.getText(), formattedDate1, "The selected date is NOT displayed in Check In box");
        System.out.println("The selected date is displayed in Check In box");

        String newFormattedDate = after3days.format(formatter);

        String newXPath = String.format("(//button[@aria-label='%s'])[2]", newFormattedDate);
        System.out.println(newXPath);


        WebElement dateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newXPath)));
        wait.withTimeout(Duration.ofSeconds(30));
        dateButton.click();

        WebElement checkOutvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_out-value")));
        String formattedDate2 = after3days.format(newFormatter);

        Assert.assertEquals(checkOutvalue.getText(), formattedDate2, "The selected date is NOT displayed in Check Out box");
        System.out.println("The selected date is displayed in Check Out box");


        //////
        WebElement counterInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .value")));
//        WebElement counterInput = driver.findElement(By.cssSelector("#adults .value"));
        WebElement decrementButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .down")));
//        WebElement decrementButton = driver.findElement(By.cssSelector("#adults .down"));
        WebElement incrementButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#adults .up")));
        int counterValue = Integer.parseInt(counterInput.getText());

        boolean isIncrementButtonDisabled = false;
        if (counterValue < 1) {
            Assert.fail("The counter value is below 1");
        }
        isIncrementButtonDisabled = incrementButton.getAttribute("disabled") != null;
        if (isIncrementButtonDisabled) {
            Assert.fail("The increment button is disabled at counter value 1");

        } else {
            System.out.println("The increment button is not disabled at counter value 1");
        }
        // Attempt to INCREMENT
        while (counterValue < 2) {
            incrementButton.click();
            counterValue = Integer.parseInt(counterInput.getText());
            System.out.println("Counter value after increment: " + counterValue);

        }
////
//        try {
//            Thread.sleep(1005000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }


        WebElement price1night = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("price")));
        String price1nightValue = price1night.getText();

        int price1nightValueInt = Integer.parseInt(price1nightValue.split("\\$")[1]);

        WebElement totalRow = driver.findElement(By.cssSelector("tr.total"));
        String totalPriceText = totalRow.findElement(By.cssSelector("td[ng-bind^='paymentDetails.total']")).getText();
        int totalPrice = Integer.parseInt(totalPriceText.split("\\$")[1]);

        System.out.println("Total" + ": " + totalPrice + " Pretul de sus pe o noapte: " + price1nightValueInt);

        List<WebElement> nightRows1 = driver.findElements(By.cssSelector("tr.nights"));

        for (WebElement row : nightRows1) {
            String nights = row.findElement(By.cssSelector("span[ng-bind='nights']")).getText();
            System.out.println("Nr nopti: "+nights);
            int nightsValueInt = Integer.parseInt(nights);
            Assert.assertEquals(nightsValueInt*price1nightValueInt, totalPrice,"The value of the second displayed price in reservation details is not total price");
            System.out.println("The value of the second displayed price in reservation details is total price");
        }

    }
}