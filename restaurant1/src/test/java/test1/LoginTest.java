package test1;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTest {

    private WebDriver driver;
    private final String baseUrl = "http://localhost:3000";

    @BeforeAll
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Desktop\\test\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterAll
   // public void teardown() {
      //  if (driver != null) {
           // driver.quit();
      //  }
   // }

    @BeforeEach
    public void openLoginPage() {
        driver.get(baseUrl + "/login");
    }

    @Test
    public void testClientLogin() throws InterruptedException {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        
        driver.get("http://localhost:3000/login");
        Thread.sleep(500);

        
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Se connecter')]"));

        usernameInput.sendKeys("Noussa");
        passwordInput.sendKeys("12345");
        loginButton.click();
        Thread.sleep(500);
    }

    @Test
    public void testAdminLogin() throws InterruptedException {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        
        driver.get("http://localhost:3000/login");

       
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Se connecter')]"));

        usernameInput.sendKeys("Admin1");
        passwordInput.sendKeys("pass123");
        loginButton.click();
        driver.get("http://localhost:3000/admin");
        Thread.sleep(500);

        
    }

    @Test
    public void testCuisinierLogin() throws InterruptedException {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

       
        driver.get("http://localhost:3000/login");

       
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Se connecter')]"));

        usernameInput.sendKeys("cook1");
        passwordInput.sendKeys("pass123");
        loginButton.click();
        
        driver.get("http://localhost:3000/cook");
        Thread.sleep(500);
    }

    @Test
    public void testLoginInvalide() throws InterruptedException {
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement submitBtn = driver.findElement(By.cssSelector("button[type='submit']"));

        usernameField.sendKeys("fakeuser");
        passwordField.sendKeys("wrongpass");
        submitBtn.click();

        Thread.sleep(1000);

        assertTrue(driver.getPageSource().contains("Identifiants invalides")
            || driver.getCurrentUrl().contains("/login"), "L'utilisateur doit rester sur /login en cas d'erreur");
    }
}


