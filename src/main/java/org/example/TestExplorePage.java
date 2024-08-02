
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

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static java.awt.SystemColor.text;


public class TestExplorePage {

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
    public void verifyPageDescription() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/explore");
        System.out.println("Page is loaded successfully");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement paragraphElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#i6kvh3dl p span.wixui-rich-text__text")));

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
    public void verifyChinaTownDescription() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/explore");
        System.out.println("Page is loaded successfully");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement paragraphElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#i6kv3ge8 p.font_8.wixui-rich-text__text:nth-of-type(4)")));


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
    public void verifyHaightAndAshburyDescription() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/explore");
        System.out.println("Page is loaded successfully");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement paragraphElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#i6kvbhmb p.font_8.wixui-rich-text__text:nth-of-type(4)")));

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
    public void verifyGondenGateBridgeDescription() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern/explore");
        System.out.println("Page is loaded successfully");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement paragraphElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#i6kvbkw0 p.font_8.wixui-rich-text__text:nth-of-type(4)")));

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


}