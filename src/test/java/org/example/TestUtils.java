package org.example;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtils {

    public static void captureScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String screenshotName = testName + "_" + timestamp + ".png";
        String screenshotPath = "target/artifacts/" + screenshotName;

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(screenshot, new File(screenshotPath));
            System.out.println("Screenshot saved: " + screenshotPath);
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }
    }
}


//private void takeScreenshot(String testName)
// {    try {
// File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
// String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//
// String filePath = "target/artifacts/" + testName + "_" + timestamp + ".png";        File
// destination = new File(filePath);
// if (!destination.getParentFile().exists()) {            destination.getParentFile().mkdirs(); // Ensure the artifacts directory exists        }        // Use Files.copy for reliable file saving
// Files.copy(screenshot.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
// System.out.println("Screenshot saved: " + destination.getAbsolutePath());    } catch (Exception e) {
// System.err.println("Error saving screenshot: " + e.getMessage());        e.printStackTrace();    }}
