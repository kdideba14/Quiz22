import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Quiz {
  WebDriver driver;

  public Quiz() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    // options.addArguments("--headless");
    // options.addArguments("--disable-dev-shm-usage");
    driver = new ChromeDriver(options);
  }

  @Test
  public void firstTest() {
    // make it headless for CI
    driver.get("https://demoqa.com/login");
    driver.manage().window().maximize();
    driver.findElement(By.id("userName")).sendKeys("test123");
    driver.findElement(By.id("password")).sendKeys("Automation@123");
    driver.findElement(By.id("login")).click();

    // why not?
    // Assert.assertEquals(driver.findElements(By.xpath("//*[@id='submit']")).size(),1);

    new WebDriverWait(driver, Duration.ofSeconds(15))
        .until(ExpectedConditions.presenceOfElementLocated(By.id("gotoStore")));
    driver.findElement(By.id("gotoStore")).click();
    new WebDriverWait(driver, Duration.ofSeconds(15))
        .until(ExpectedConditions.presenceOfElementLocated(By.className("rt-tbody")));
    WebElement books = driver.findElement(By.className("rt-tbody"));
    List<WebElement> count = books.findElements(By.className("action-buttons"));
    Assert.assertEquals(8,count.size());
    
    driver.findElement(By.xpath("//*[@id='see-book-Git Pocket Guide']/a")).click();
    Assert.assertEquals(driver.findElement(By.xpath("//*[@id='userName-value']")).getText(),"Git Pocket Guide");

    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[2]/div[9]/div[1]/button")).click();
  
    // they have same id and xpath :(
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[2]/div[9]/div[2]/button")).click();
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[2]/div[9]/div[2]/button")).click();

    new WebDriverWait(driver, Duration.ofSeconds(15))
        .until(ExpectedConditions.presenceOfElementLocated(By.className("rt-body")));
    WebElement books2 = driver.findElement(By.className("rt-tbody"));
    List<WebElement> count2 = books2.findElements(By.className("action-buttons"));
    Assert.assertEquals(8,count2.size());

    driver.findElement(By.xpath("//*[@id=;submit']")).click();
  }


}
