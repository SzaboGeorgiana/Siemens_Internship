
package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static java.awt.SystemColor.text;


public class TestChatWidget {
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
    public void testChatButton() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern");
        System.out.println("Page is loaded successfully");

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"comp-jr4sqg2g\"]/iframe")));
        driver.switchTo().frame(iframe);

        WebElement chat = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div")));
        Assert.assertTrue(chat.isDisplayed(), "Chat button is not displayed");
        System.out.println("Chat button is displayed");

        chat.click();

        WebElement textBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/textarea")));
        Assert.assertTrue(textBox.isDisplayed(), "Chat text box is not displayed.The chat does not opened!");
        System.out.println("The chat is open");

        // Trimite un text de test în textBox
        textBox.sendKeys("Test Message");

        String textBoxMessage = textBox.getAttribute("value");
        System.out.println("The message in the text box is: " + textBoxMessage);

    }

    @Test
    public void testMessage_No_Name() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern");
        System.out.println("Page is loaded successfully");

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"comp-jr4sqg2g\"]/iframe")));
        driver.switchTo().frame(iframe);

        //Validate that the Chat button exists
        WebElement chat = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div")));
        Assert.assertTrue(chat.isDisplayed(), "Chat button is not displayed");
        System.out.println("Chat button is displayed");
        //Click on the Chat button
        chat.click();


        WebElement textBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/textarea")));
        Assert.assertTrue(textBox.isDisplayed(), "Chat text box is not displayed.The chat does not opened!");
        System.out.println("The chat is open");

        // Type message into the text field

        String key = "Test Message";
        textBox.sendKeys(key);

        String textBoxMessage = textBox.getAttribute("value");
        System.out.println("The message in the text box is: " + textBoxMessage);
        Assert.assertEquals(textBoxMessage, key, "The message is not the same");
        System.out.println("Message is displayed in the input textbox");

        //Validate that send button exists
        WebElement sentButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[2]")));
        Assert.assertTrue(sentButton.isDisplayed(), "The sent button is not displayed.");
        System.out.println("The sent button is displayed.");
        //Click send button
        sentButton.click();

        WebElement sentMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chat-messages-list\"]/div[2]/div/div/div/div[1]/div/div/div/div/div/div")));
        String message = sentMessage.getText();
        System.out.println("The sent message is: " + message);
        Assert.assertEquals(key, message, "The message was not send correctly!");
        System.out.println("The message is sent");

        WebElement chatForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chat-messages-list\"]/div[3]/div/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/form")));
        Assert.assertTrue(chatForm.isDisplayed(), "but, Chat form is not sent back!");
        System.out.println("and a form appears as a reply");

//        try {
//            Thread.sleep(1005000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        //////////////////////

        //Click on Submit button
        WebElement formSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("dUbg3")));
        Assert.assertTrue(formSubmitButton.isDisplayed(), "Chat form Submit button is not displayed!");

        System.out.println("Chat form Submit button is displayed!");


        // Verificăm dacă `textfield`-ul de nume are focus
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"name\"]")));
        nameField.click();
        // Asigurăm că focusul este pe `textfield`-ul de nume
        Assert.assertEquals(driver.switchTo().activeElement(), nameField, "The cursor doesn't appears inside the field");
        System.out.println("The cursor appears inside the field");

        formSubmitButton.click();

        WebElement warningIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("s3R8NnC")));
        warningIcon.click();
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]")));//*[@id="name-error"]/div/div/svg
        String expectedErrorMessage = "Make sure to add your name.";
        String actualErrorMessage = errorMessage.getText();

        Assert.assertTrue(warningIcon.isDisplayed(), "Warning icon is not displayed.");
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed.");
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "The error message is incorrect.");
        System.out.println("A warning icon and the correct error message are displayed");

    }


    @Test
    public void testMessage_invalid_Email() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern");
        System.out.println("Page is loaded successfully");

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"comp-jr4sqg2g\"]/iframe")));
        driver.switchTo().frame(iframe);

        //Validate that the Chat button exists
        WebElement chat = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div")));
        Assert.assertTrue(chat.isDisplayed(), "Chat button is not displayed");
        System.out.println("Chat button is displayed");
        //Click on the Chat button
        chat.click();


        WebElement textBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/textarea")));
        Assert.assertTrue(textBox.isDisplayed(), "Chat text box is not displayed.The chat does not opened!");
        System.out.println("The chat is open");

        // Type message into the text field

        String key = "Test Message";
        textBox.sendKeys(key);

        String textBoxMessage = textBox.getAttribute("value");
        System.out.println("The message in the text box is: " + textBoxMessage);
        Assert.assertEquals(textBoxMessage, key, "The message is not the same");
        System.out.println("Message is displayed in the input textbox");

        //Validate that send button exists
        WebElement sentButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[2]")));
        Assert.assertTrue(sentButton.isDisplayed(), "The sent button is not displayed.");
        System.out.println("The sent button is displayed.");
        //Click send button
        sentButton.click();

        WebElement sentMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chat-messages-list\"]/div[2]/div/div/div/div[1]/div/div/div/div/div/div")));
        String message = sentMessage.getText();
        System.out.println("The sent message is: " + message);
        Assert.assertEquals(key, message, "The message was not send correctly!");
        System.out.println("The message is sent");

        WebElement chatForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chat-messages-list\"]/div[3]/div/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/form")));
        Assert.assertTrue(chatForm.isDisplayed(), "but, Chat form is not sent back!");
        System.out.println("and a form appears as a reply");

//        try {
//            Thread.sleep(1005000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        //////////////////////

        //Click on Submit button
        WebElement formSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("dUbg3")));
        Assert.assertTrue(formSubmitButton.isDisplayed(), "Chat form Submit button is not displayed!");

        System.out.println("Chat form Submit button is displayed!");

        // Verificăm dacă `textfield`-ul de nume are focus
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"name\"]")));
        nameField.click();
        // Asigurăm că focusul este pe `textfield`-ul de nume
        Assert.assertEquals(driver.switchTo().activeElement(), nameField, "The cursor doesn't appears inside the field");
        System.out.println("The cursor appears inside the field");

// Fill in the name field
        nameField.sendKeys("Georgiana");
        System.out.println("Name field is filled.");

// Fill in the email field with an invalid email
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"email\"]")));
        emailField.sendKeys("invalid-email");
        System.out.println("Email field is filled with an invalid email.");

// Click on Submit button
        formSubmitButton.click();

// Validate the appearance of the warning icon and error message
        WebElement warningIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("s3R8NnC")));
        warningIcon.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]")));
        String expectedErrorMessage = "Enter a valid email address.";
        String actualErrorMessage = errorMessage.getText();

        Assert.assertTrue(warningIcon.isDisplayed(), "Warning icon is not displayed.");
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed.");
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "The error message is incorrect.");
        System.out.println("A warning icon and the correct error message are displayed for the invalid email.");

    }


    @Test
    public void testMessage_valid_Data() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern");
        System.out.println("Page is loaded successfully");

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"comp-jr4sqg2g\"]/iframe")));
        driver.switchTo().frame(iframe);

        //Validate that the Chat button exists
        WebElement chat = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div")));
        Assert.assertTrue(chat.isDisplayed(), "Chat button is not displayed");
        System.out.println("Chat button is displayed");
        //Click on the Chat button
        chat.click();


        WebElement textBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/textarea")));
        Assert.assertTrue(textBox.isDisplayed(), "Chat text box is not displayed.The chat does not opened!");
        System.out.println("The chat is open");

        // Type message into the text field

        String key = "Test Message";
        textBox.sendKeys(key);

        String textBoxMessage = textBox.getAttribute("value");
        System.out.println("The message in the text box is: " + textBoxMessage);
        Assert.assertEquals(textBoxMessage, key, "The message is not the same");
        System.out.println("Message is displayed in the input textbox");

        //Validate that send button exists
        WebElement sentButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[2]")));
        Assert.assertTrue(sentButton.isDisplayed(), "The sent button is not displayed.");
        System.out.println("The sent button is displayed.");
        //Click send button
        sentButton.click();

        WebElement sentMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chat-messages-list\"]/div[2]/div/div/div/div[1]/div/div/div/div/div/div")));
        String message = sentMessage.getText();
        System.out.println("The sent message is: " + message);
        Assert.assertEquals(key, message, "The message was not send correctly!");
        System.out.println("The message is sent");

        WebElement chatForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chat-messages-list\"]/div[3]/div/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/form")));
        Assert.assertTrue(chatForm.isDisplayed(), "but, Chat form is not sent back!");
        System.out.println("and a form appears as a reply");

        //////////////////////

        //Click on Submit button
        WebElement formSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("dUbg3")));
        Assert.assertTrue(formSubmitButton.isDisplayed(), "Chat form Submit button is not displayed!");

        System.out.println("Chat form Submit button is displayed!");

        // Verificăm dacă `textfield`-ul de nume are focus
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"name\"]")));
        nameField.click();
        // Asigurăm că focusul este pe `textfield`-ul de nume
        Assert.assertEquals(driver.switchTo().activeElement(), nameField, "The cursor doesn't appears inside the field");
        System.out.println("The cursor appears inside the field");


// Fill in the name field
        nameField.sendKeys("Georgiana");
        System.out.println("Name field is filled.");

// Fill in the email field
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"email\"]")));
        emailField.sendKeys("georgiana@gmail.com");
        System.out.println("Email field is filled.");

// Click on Submit button
        formSubmitButton.click();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            WebElement nameField1 =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"name\"]")));
            Assert.fail("Chat text box is still displayed. The Form is not sent!");
        } catch (TimeoutException e) {
            // If the exception is caught, it means the name field is not present, which is expected
            System.out.println("The form is sent successfully ");
        }


        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("lso49")));
        Assert.assertTrue(confirmationMessage.isDisplayed(), "but, a confirmation message is not displayed");
        System.out.println("and a confirmation message is displayed");


    }
}