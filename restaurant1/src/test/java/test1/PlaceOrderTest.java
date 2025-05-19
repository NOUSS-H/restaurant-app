package test1;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class PlaceOrderTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\user\\\\Desktop\\\\test\\\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    @Test
    public void testClientAjouteCommandeEtVoitDansDashboard() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
       
        driver.get("http://localhost:3000/login");

      
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Se connecter')]"));

        usernameInput.sendKeys("Noussa");
        passwordInput.sendKeys("12345");
        loginButton.click();

        driver.get("http://localhost:3000/menu");
       
        WebElement addButton = driver.findElement(By.xpath("//button[contains(text(),'Ajouter')]"));
        
        
        addButton.click();

       
        driver.get("http://localhost:3000/cart");

       
        WebElement commanderBtn = driver.findElement(By.xpath("//button[contains(text(),'Commander')]"));
        commanderBtn.click();

       
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        assertTrue(alert.getText().contains("Commande passée avec succès"));
        alert.accept();

        
        WebElement dashboardLink = driver.findElement(By.xpath("//a[contains(text(),'Mon compte')]"));
        dashboardLink.click();

    
     WebElement body = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
     String pageText = body.getText();
     assertTrue(pageText.contains("Commande") || pageText.contains("Plat") || pageText.contains("En cours"));
    }

    @After
    public void tearDown() {
        //driver.quit();
    }  
} 