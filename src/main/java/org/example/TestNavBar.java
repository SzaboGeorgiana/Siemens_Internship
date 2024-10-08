package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.awt.SystemColor.text;


public class TestNavBar {

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
    public void verifyHomeButton() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern");
        System.out.println("Page is loaded successfully");

        String search_text = "HOME";
        WebElement button = driver.findElement(By.id("i6kl732v0label"));


        if (button.isDisplayed()) {
            System.out.println("The button is displayed");

            String text = button.getText();
            Assert.assertEquals(text, search_text, "Text not correct!");
            System.out.println("The name of the button is Home");

            button.click();

            String currentUrl = driver.getCurrentUrl();
            Assert.assertEquals(currentUrl, "https://ancabota09.wixsite.com/intern", "Bad redirect");
            System.out.println("The Home page is loaded successfully");

        } else {
            System.out.println("The Home button is not displayed");
            Assert.fail();
        }
    }



    @Test
    public void verifyExploreButton() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern");
        System.out.println("Page is loaded successfully");

        String search_text = "EXPLORE";
        WebElement button = driver.findElement(By.id("i6kl732v1label"));


        if (button.isDisplayed()) {
            System.out.println("The button is displayed");

            String text = button.getText();
            Assert.assertEquals(text, search_text, "Text not correct!");
            System.out.println("The name of the button is EXPLORE");

            button.click();

            String currentUrl = driver.getCurrentUrl();
            Assert.assertEquals(currentUrl, "https://ancabota09.wixsite.com/intern/explore", "Bad redirect");
            System.out.println("The EXPLORE page is loaded successfully");

        } else {
            System.out.println("The EXPLORE button is not displayed");
            Assert.fail();
        }
    }



    @Test
    public void verifyRoomsButton() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern");
        System.out.println("Page is loaded successfully");

        String search_text = "ROOMS";
        WebElement button = driver.findElement(By.id("i6kl732v2label"));


        if (button.isDisplayed()) {
            System.out.println("The button is displayed");

            String text = button.getText();
            Assert.assertEquals(text, search_text, "Text not correct!");
            System.out.println("The name of the button is ROOMS");

            button.click();

            String currentUrl = driver.getCurrentUrl();
            Assert.assertEquals(currentUrl, "https://ancabota09.wixsite.com/intern/rooms", "Bad redirect");
            System.out.println("The ROOMS page is loaded successfully");

        } else {
            System.out.println("The ROOMS button is not displayed");
            Assert.fail();
        }
    }



    @Test
    public void verifyContactButton() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern");
        System.out.println("Page is loaded successfully");

        String search_text = "CONTACT";
        WebElement button = driver.findElement(By.id("i6kl732v3label"));


        if (button.isDisplayed()) {
            System.out.println("The button is displayed");

            String text = button.getText();
            Assert.assertEquals(text, search_text, "Text not correct!");
            System.out.println("The name of the button is CONTACT");

            button.click();

            String currentUrl = driver.getCurrentUrl();
            Assert.assertEquals(currentUrl, "https://ancabota09.wixsite.com/intern/contact", "Bad redirect");
            System.out.println("The CONTACT page is loaded successfully");

        } else {
            System.out.println("The CONTACT button is not displayed");
            Assert.fail();
        }
    }



    @Test
    public void verifyBookNowButton() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern");
        System.out.println("Page is loaded successfully");

        String search_text = "BOOK NOW";
//        WebElement button = driver.findElement(By.id("i6kl732v0label"));
        WebElement button = driver.findElement(By.cssSelector(".l7_2fn.wixui-button__label"));


        if (button.isDisplayed()) {
            System.out.println("The button is displayed");

            String text = button.getText();
            Assert.assertEquals(text, search_text, "Text not correct!");
            System.out.println("The name of the button is BOOK NOW");

            button.click();

            String currentUrl = driver.getCurrentUrl();
            Assert.assertEquals(currentUrl, "https://ancabota09.wixsite.com/intern/booknow", "Bad redirect");
            System.out.println("The BOOK NOW page is loaded successfully");

        } else {
            System.out.println("The BOOK NOW button is not displayed");
            Assert.fail();
        }
    }



    @Test
    public void verifyHomeAndAwayButton() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern");
        System.out.println("Page is loaded successfully");

        String search_text = "HOME & AWAY";
        WebElement button = driver.findElement(By.id("i6ksxrtk"));


        if (button.isDisplayed()) {
            System.out.println("The button is displayed");

            String text = button.getText();
            Assert.assertEquals(text, search_text, "Text not correct!");
            System.out.println("The name of the button is HOME&AWAY");

            button.click();

            String currentUrl = driver.getCurrentUrl();
            Assert.assertEquals(currentUrl, "https://ancabota09.wixsite.com/intern", "Bad redirect");
            System.out.println("The HOME&AWAY page is loaded successfully");

        } else {
            System.out.println("The HOME&AWAY button is not displayed");
            Assert.fail();
        }
    }

}

