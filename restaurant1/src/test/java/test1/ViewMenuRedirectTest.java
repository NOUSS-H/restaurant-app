package test1;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ViewMenuRedirectTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\user\\\\Desktop\\\\test\\\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testViewMenuButtonRedirectsToMenuPage() throws InterruptedException {
        driver.get("http://localhost:3000/");
        Thread.sleep(500);

       
        WebElement menuButton = driver.findElement(By.xpath("//a[contains(text(),'Voir le Menu')]"));
        menuButton.click();

        Thread.sleep(1500);

        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/menu"));
    }

    @AfterEach
    public void tearDown() {
        //driver.quit();
    }
}

