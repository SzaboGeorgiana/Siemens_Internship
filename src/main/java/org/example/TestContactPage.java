package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestContactPage {

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
    public void verifyDescriptionParagraph() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/contact");
        System.out.println("Page is loaded successfully");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement paragraphElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i6ly3ckc_0")));

        String paragraphText = paragraphElement.getText();
        System.out.println("Paragraph text: " + paragraphText);


        String expectedText1 = "I'm a paragraph. Click here to add your own text and edit me. It’s easy. Just click “Edit Text” or double click me to add your own content and make changes to the font. Feel free to drag and drop me anywhere you like on your page. I’m a great place for you to tell a story and let your users know a little more about you.";
        String expectedText2 = "I'm a paragraph. Click here to add your own text and edit me. I’m a great place for you to tell a story and let your users know a little more about you.";

        if (!paragraphText.isEmpty() && !paragraphText.equals(expectedText1) && !paragraphText.equals(expectedText2)) {
            System.out.println("The description paragraph contains relevant information");
        } else {
            System.out.println("The description paragraph doesn't contain relevant information");
            Assert.fail("Paragraph text does not contain relevant information.");
        }
    }

    @Test
    public void validateConfirmationMessageInitiallyHidden() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/contact");
        System.out.println("Page is loaded successfully");

        WebElement contactForm = driver.findElement(By.id("comp-jxbsa1dm"));
        Assert.assertTrue(contactForm.isDisplayed(), "The contact form is not displayed");
        System.out.println("The contact form is displayed");

        WebElement confirmationMessage = driver.findElement(By.xpath("//*[@id=\"comp-jxbsa1fv\"]/p"));
        Assert.assertNotEquals(confirmationMessage.getText(), "Thanks for submitting!", "The confirmation message is initially displayed:");
        System.out.println("The confirmation message is not initially displayed");
    }

    @Test
    public void validateRequiredFieldWarning_Name() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/contact");
        System.out.println("Page is loaded successfully");

        WebElement contactForm = driver.findElement(By.id("comp-jxbsa1dm"));
        Assert.assertTrue(contactForm.isDisplayed(), "The contact form is not displayed");
        System.out.println("The contact form is displayed");
        WebElement submitButton = contactForm.findElement(By.xpath("//*[@id=\"comp-jxbsa1fi\"]/button"));

        WebElement nameField = driver.findElement(By.id("input_comp-jxbsa1e9"));

        Assert.assertTrue(nameField.getAttribute("required") != null, "The Name field is not marked as required.");

        submitButton.click();

        String validationMessage = nameField.getAttribute("validationMessage");
        Assert.assertEquals(validationMessage, "Please fill out this field.", "Browser validation message not displayed as expected.");
        System.out.println("Warning message for the required field Name is displayed");
    }


    @Test
    public void validateRequiredFieldWarning_Email() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/contact");
        System.out.println("Page is loaded successfully");

        WebElement contactForm = driver.findElement(By.id("comp-jxbsa1dm"));
        Assert.assertTrue(contactForm.isDisplayed(), "The contact form is not displayed");
        System.out.println("The contact form is displayed");
        WebElement submitButton = contactForm.findElement(By.xpath("//*[@id=\"comp-jxbsa1fi\"]/button"));

        WebElement nameField = driver.findElement(By.id("input_comp-jxbsa1e9"));

        nameField.sendKeys("Georgiana");
        Assert.assertEquals(nameField.getAttribute("value"), "Georgiana", "The entered name is not displayed in the Name field");
        System.out.println("The entered name is displayed in the Name field");

        submitButton.click();

        WebElement emailField = contactForm.findElement(By.id("input_comp-jxbsa1em"));
        String validationMessage = emailField.getAttribute("validationMessage");
        Assert.assertEquals(validationMessage, "Please fill out this field.", "Warning message is not displayed for the required field Email");
        System.out.println("Warning message for the required field Email is displayed");

    }


    @Test
    public void validateRequiredFieldWarning_Message() {

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            System.out.println("Loaded successfully");

            driver.get("https://ancabota09.wixsite.com/intern/contact");
            System.out.println("Page is loaded successfully");

            WebElement contactForm = driver.findElement(By.id("comp-jxbsa1dm"));
            Assert.assertTrue(contactForm.isDisplayed(), "The contact form is not displayed");
            System.out.println("The contact form is displayed");
            WebElement submitButton = contactForm.findElement(By.xpath("//*[@id=\"comp-jxbsa1fi\"]/button"));

            WebElement nameField = driver.findElement(By.id("input_comp-jxbsa1e9"));

            nameField.sendKeys("Georgiana");
            Assert.assertEquals(nameField.getAttribute("value"), "Georgiana", "The entered name is not displayed in the Name field");
        System.out.println("The entered name is displayed in the Name field");

            WebElement emailField = contactForm.findElement(By.name("email"));
            emailField.sendKeys("georgiana@gmail.com");
            Assert.assertEquals(emailField.getAttribute("value"), "georgiana@gmail.com", "The entered Email is not displayed in the Email field");
        System.out.println("The entered email is displayed in the Name field");

            submitButton.click();

            WebElement messageField = contactForm.findElement(By.id("textarea_comp-jxbsa1f7"));
            String validationMessage = messageField.getAttribute("validationMessage");
            Assert.assertEquals(validationMessage, "Please fill out this field.", "Warning message is not displayed for the required field Message");

        System.out.println("Warning message for the required field message is displayed");

    }


    @Test
    public void validateFieldWarning_InvalidEmail() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/contact");
        System.out.println("Page is loaded successfully");
        WebElement contactForm = driver.findElement(By.id("comp-jxbsa1dm"));
        Assert.assertTrue(contactForm.isDisplayed(), "The contact form is not displayed");
        System.out.println("The contact form is displayed");
        WebElement submitButton = contactForm.findElement(By.xpath("//*[@id=\"comp-jxbsa1fi\"]/button"));

        WebElement nameField = driver.findElement(By.id("input_comp-jxbsa1e9"));

        nameField.sendKeys("Georgiana");
        Assert.assertEquals(nameField.getAttribute("value"), "Georgiana", "The entered name is not displayed in the Name field");

        WebElement emailField = contactForm.findElement(By.name("email"));
        emailField.sendKeys("invalid-email");
        Assert.assertEquals(emailField.getAttribute("value"), "invalid-email", "The entered email is not displayed in the Email field");

        submitButton.click();

        String validationMessage = emailField.getAttribute("validationMessage");
        Assert.assertEquals(validationMessage, "Please include an '@' in the email address. 'invalid-email' is missing an '@'.", "A warning message is not displayed for the Email field");
    }

    @Test
    public void validateMapFullscreen() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/contact");
        System.out.println("Page is loaded successfully");

        WebElement mapElement = driver.findElement(By.id("i6lyzjsh"));
        Assert.assertTrue(mapElement.isDisplayed(), "The map is not displayed");
        System.out.println("The map is displayed");
        driver.switchTo().frame(mapElement.findElement(By.tagName("iframe")));

        // Validate that the Fullscreen button exists
        WebElement fullscreenButton = driver.findElement(By.cssSelector(".gm-fullscreen-control"));
        Assert.assertTrue(fullscreenButton.isDisplayed(), "Fullscreen button is not displayed");
        System.out.println("Fullscreen button is displayed");

        // Click on Fullscreen button
        fullscreenButton.click();

        // Use JavaScript to check if the document is in fullscreen mode
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Boolean isFullscreen = (Boolean) jsExecutor.executeScript("return document.fullscreenElement != null;");

        Assert.assertTrue(isFullscreen, "The map is not displayed in fullscreen mode");

        driver.switchTo().defaultContent();
        System.out.println("The map is displayed in fullscreen \n");

    }


    @Test
    public void validateMapPinAddress() throws MalformedURLException, UnsupportedEncodingException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/contact");
        System.out.println("Page is loaded successfully");

        WebElement mapElement = driver.findElement(By.id("i6lyzjsh"));
        Assert.assertTrue(mapElement.isDisplayed(), "The map is not displayed");
        System.out.println("The map is displayed");

        driver.switchTo().frame(mapElement.findElement(By.tagName("iframe")));

        WebElement fullscreenButton = driver.findElement(By.cssSelector(".gm-fullscreen-control"));
        Assert.assertTrue(fullscreenButton.isDisplayed(), "Fullscreen button is not displayed");
//        System.out.println("Fullscreen button is displayed");

        WebElement addressElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"map_canvas\"]/div/div[3]/div[13]/div/a"))); // Adjust selector as needed
        String style = addressElement.getAttribute("href");

        System.out.println(style);

        String urlString=style;
        String decodedUrl = URLDecoder.decode(urlString, StandardCharsets.UTF_8.name());

        // Parsează URL-ul
        URL url = new URL(decodedUrl);
        String query = url.getQuery();

        // Extrage parametrii din URL
        Map<String, String> params = new HashMap<>();
        for (String param : query.split("&")) {
            String[] pair = param.split("=");
            if (pair.length == 2) {
                params.put(pair[0], pair[1]);
            }
        }
        double latitude = 0;
        double longitude = 0;
        // Extrage valorile pentru latitudine și longitudine
        String ll = params.get("ll");
        if (ll != null) {
            String[] coordinates = ll.split(",");
            if (coordinates.length == 2) {
                 latitude = Double.parseDouble(coordinates[0]);
                 longitude = Double.parseDouble(coordinates[1]);

                // Afișează valorile
                System.out.println("Latitude: " + latitude);
                System.out.println("Longitude: " + longitude);
            }
        }
        double sanFranciscoLatitudeMin = 37.6;
        double sanFranciscoLatitudeMax = 37.9;
        double sanFranciscoLongitudeMin = -123.0;
        double sanFranciscoLongitudeMax = -122.3;

        // Verifică dacă coordonatele se află în intervalul pentru San Francisco
        boolean isInSanFrancisco = latitude >= sanFranciscoLatitudeMin &&
                latitude <= sanFranciscoLatitudeMax &&
                longitude >= sanFranciscoLongitudeMin &&
                longitude <= sanFranciscoLongitudeMax;

        // Afișează rezultatul
        if (isInSanFrancisco) {
            System.out.println("Coordonatele se află în San Francisco.\nThe address on the map is correctly displayed\n");
        } else {
            Assert.fail("Coordonatele NU se află în San Francisco.");
        }

    }
    @Test
    public void validateMapDragging() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/contact");
        System.out.println("Page is loaded successfully");

        WebElement mapElement = driver.findElement(By.id("i6lyzjsh"));
        Assert.assertTrue(mapElement.isDisplayed(), "The map is not displayed");
        System.out.println("The map is displayed");

        driver.switchTo().frame(mapElement.findElement(By.tagName("iframe")));
        // Perform a series of drag-and-drop actions
        Actions actions = new Actions(driver);

        wait.withTimeout(Duration.ofSeconds(60));

        WebElement fullscreenButton = driver.findElement(By.cssSelector(".gm-fullscreen-control"));
        Assert.assertTrue(fullscreenButton.isDisplayed(), "Fullscreen button is not displayed");
//        System.out.println("Fullscreen button is displayed");

        fullscreenButton.click();

        // Drag the map in various directions (up, down, left, right)
        actions.clickAndHold().moveByOffset(0, 100).release().perform(); // Drag down
        actions.clickAndHold().moveByOffset(0, -100).release().perform(); // Drag up
        actions.clickAndHold().moveByOffset(100, 0).release().perform(); // Drag right
        actions.clickAndHold().moveByOffset(-100, 0).release().perform(); // Drag left

        System.out.println("Map dragging actions performed successfully");
    }


}
