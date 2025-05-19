package test1;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminDashbordTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\user\\\\Desktop\\\\test\\\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void testAdminLoginAndViewAllOrders() throws InterruptedException{
       
        driver.get("http://localhost:3000/login");

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        WebElement password = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Se connecter')]"));

        username.sendKeys("Admin1");
        password.sendKeys("pass123");
        loginButton.click();
        Thread.sleep(500);
        
        wait.until(ExpectedConditions.urlContains("/admin"));
        Thread.sleep(500);

     
        WebElement content = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        String text = content.getText();
        assertTrue(text.contains("Commande") || text.contains("Client") || text.contains("Plat"));
    }

    @AfterEach
    public void tearDown() {
       // driver.quit();
    }
}
