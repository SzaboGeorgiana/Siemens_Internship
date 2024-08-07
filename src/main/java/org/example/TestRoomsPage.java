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

import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestRoomsPage {

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
    public void verifySearchWith2Adults() {
    }
    @Test
    public void verifySearchWithCheckOutOneDayAfterCheckIn() {
    }
    @Test
    public void verifySearchWith2Kids() {
    }
    @Test
    public void verifySearchWith8Adults() {
    }
    @Test
    public void verifySearchAgainButton() {
    }

    @Test
    public void verifyClearButton() {
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


