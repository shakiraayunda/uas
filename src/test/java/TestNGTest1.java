/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author Pluto 01
 */
public class TestNGTest1 {
    
    WebDriver driver = null;

    @BeforeTest
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @Test
    public void firstTest(){
        driver.get("http://pluto18.epizy.com");
        driver.findElement(id("username")).sendKeys("admin");
        driver.findElement(id("password")).sendKeys("admin123");
        driver.findElement(xpath("/html/body/div/form/input[3]")).click();
        String expectedResult = "Welcome back, admin!";
        String actualResult = driver.findElement(xpath("/html/body/div/p")).getText();
        Assert.assertEquals(actualResult, expectedResult);
    }
    
    @Test
    public void secondTest(){
        driver.get("http://pluto18.epizy.com");
        driver.findElement(id("username")).sendKeys("false_admin");
        driver.findElement(id("password")).sendKeys("admin123");
        driver.findElement(xpath("/html/body/div/form/input[3]")).click();
        String expectedResult = "Wrong username or password!";
        String actualResult = driver.findElement(className("notif")).getText();
        Assert.assertEquals(actualResult, expectedResult);
    }
    
    @Test
    public void thirdTest(){
        driver.get("http://pluto18.epizy.com");
        driver.findElement(id("username")).sendKeys("admin");
        driver.findElement(id("password")).sendKeys("false_admin123");
        driver.findElement(xpath("/html/body/div/form/input[3]")).click();
        String expectedResult = "Wrong username or password!";
        String actualResult = driver.findElement(className("notif")).getText();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @AfterTest
    public void tearDown(){
        driver.close();
        driver.quit();
    }

}
