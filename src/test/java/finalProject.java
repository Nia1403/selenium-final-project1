import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

import static java.lang.Thread.sleep;

public class finalProject {
    WebDriver driver;
  @BeforeMethod
    public void open(){
      WebDriverManager.chromedriver().setup();
      driver = new ChromeDriver();
      driver.manage().window().maximize();
  }

  @Test
    public void firstTest() throws InterruptedException {
      driver.get("https://www.swoop.ge/");
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      WebElement shesvlaButton = driver.findElement(By.xpath("//div[@class = 'HeaderTools swoop-login']"));
      shesvlaButton.click();
      //send data in Felds and click
      WebElement emailFeld = driver.findElement(By.id("emailinp"));
      emailFeld.sendKeys("nia.nia@gmail.de");
      WebElement passwotFeld = driver.findElement(By.id("feed-password"));
      passwotFeld.sendKeys("12345678");
      WebElement autorizaciaButton = driver.findElement(By.id("AuthBtn"));
      autorizaciaButton.click();

      //check if autorization Text is same as wished
      WebElement autorizacInfoTextxpath = driver.findElement(By.xpath("//*[@id='authInfo' and @style = 'display: block;']"));
      JavascriptExecutor js = (JavascriptExecutor) driver;
      String textAutirizaion = (String) js.executeScript("return arguments[0].innerText;", autorizacInfoTextxpath);
      Assert.assertEquals(textAutirizaion,"მეილი ან პაროლი არასწორია, თუ დაგავიწყდათ პაროლი,გთხოვთ ისარგებლოთ პაროლის აღდგენის ფუნქციით!");

      //check if passwortfield ist empty
      String hiddenTextinPasswort = passwotFeld.getAttribute("placeholder");
      Assert.assertEquals(hiddenTextinPasswort,"პაროლი");

  }

  @Test
  public void secondTest(){
    driver.get("https://www.swoop.ge/");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    WebElement shesvlaButton = driver.findElement(By.xpath("//div[@class = 'HeaderTools swoop-login']"));
    shesvlaButton.click();


  }



@AfterMethod
    public void close(){
      driver.quit();
}


}
