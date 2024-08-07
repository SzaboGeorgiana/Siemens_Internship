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
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.awt.SystemColor.text;


public class TestSocialMediaIcons {

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
    public void verifyFacebookButton() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern");
        System.out.println("Page is loaded successfully");

        WebElement button = driver.findElement(By.id("i0odz-i6rlbitx"));

        if (button.isDisplayed()) {
            System.out.println("The button is displayed");

            button.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 secunde de așteptare
//            wait.until(ExpectedConditions.urlToBe("https://www.facebook.com/wix"));
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            // Schimbă contextul la noua fereastră/tab
            Set<String> allWindows = driver.getWindowHandles();
            for (String windowHandle : allWindows) {
               {
                    driver.switchTo().window(windowHandle);
                }
            }

            // Verifică URL-ul în noua fereastră/tab
            String currentUrl = driver.getCurrentUrl();
//            System.out.println("Current URL: " + currentUrl);

            Assert.assertEquals(currentUrl, "https://www.facebook.com/wix", "Bad redirect");
            System.out.println("The Facebook page is loaded successfully");
        }
        else
        {
            System.out.println("The Facebook button is not displayed");
            Assert.fail();
        }
    }

    @Test
    public void verifyTwitterButton() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern");
        System.out.println("Page is loaded successfully");

        WebElement button = driver.findElement(By.id("i220sc-i6rlbitx"));

        if (button.isDisplayed()) {
            System.out.println("The button is displayed");

            button.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 secunde de așteptare
//            wait.until(ExpectedConditions.urlToBe("https://x.com/wix"));
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            // Schimbă contextul la noua fereastră/tab
            Set<String> allWindows = driver.getWindowHandles();
            for (String windowHandle : allWindows) {
                {
                    driver.switchTo().window(windowHandle);
                }
            }

            // Verifică URL-ul în noua fereastră/tab
            String currentUrl = driver.getCurrentUrl();
//            System.out.println("Current URL: " + currentUrl);

            Assert.assertEquals(currentUrl, "https://x.com/wix", "Bad redirect");
            System.out.println("The Twitter page is loaded successfully");
        }
        else
        {
            System.out.println("The Twitter button is not displayed");
            Assert.fail();
        }
    }


    @Test
    public void verifyPinterestButton() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern");
        System.out.println("Page is loaded successfully");

        WebElement button = driver.findElement(By.id("i3175p-i6rlbitx"));

        if (button.isDisplayed()) {
            System.out.println("The button is displayed");

            button.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 secunde de așteptare
//            wait.until(ExpectedConditions.urlToBe("https://www.pinterest.com/wixcom/"));
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            // Schimbă contextul la noua fereastră/tab
            Set<String> allWindows = driver.getWindowHandles();
            for (String windowHandle : allWindows) {
                {
                    driver.switchTo().window(windowHandle);
                }
            }

            // Verifică URL-ul în noua fereastră/tab
            String currentUrl = driver.getCurrentUrl();
//            System.out.println("Current URL: " + currentUrl);

            Assert.assertEquals(currentUrl, "https://www.pinterest.com/wixcom/", "Bad redirect");
            System.out.println("The Pinterest page is loaded successfully");
        }
        else
        {
            System.out.println("The Pinterest button is not displayed");
            Assert.fail();
        }
    }


    @Test
    public void verifyMailButton() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Loaded successfully");

        driver.get("https://ancabota09.wixsite.com/intern");
        System.out.println("Page is loaded successfully");

        WebDriverWait wait11 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait11.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[data-auto-recognition='true'].wixui-rich-text__text")));
        String search_text="info@mysite.com";

        if (button.isDisplayed()) {
            System.out.println("The button is displayed");

            String text = button.getText();
            Assert.assertEquals(text, search_text, "Text not correct!");
            System.out.println("The name of the button is info@mysite.com");

            button.click();

            wait11.until(driver -> true);

            //Verify the href attribute contains the mailto link
            String href = button.getAttribute("href");
            Assert.assertTrue(href.startsWith("mailto:"), "The Contact link does not contain a 'mailto' link.");
            System.out.println("The Contact link contain a 'mailto' link.");

            //Check the specific email address if needed
            Assert.assertTrue(href.contains("info@mysite.com"), "The contact options are not correct.");
            System.out.println("The contact options are correct.");

        }
        else
        {
//            System.out.println("The Mail button is not displayed");
            Assert.fail("The Mail button is not displayed");
        }
    }



}
