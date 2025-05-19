  package test1;




import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

public class AddToCartTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Desktop\\test\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testAddToCartButtonAfterLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
       
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

       
    }

    @AfterEach
    public void tearDown() {
       // driver.quit();
    }
}