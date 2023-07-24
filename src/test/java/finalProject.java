import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;


public class finalProject {
  WebDriver driver;

  @BeforeMethod
  public void open() {
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
    Assert.assertEquals(textAutirizaion, "მეილი ან პაროლი არასწორია, თუ დაგავიწყდათ პაროლი,გთხოვთ ისარგებლოთ პაროლის აღდგენის ფუნქციით!");

    //check if passwortfield ist empty
    String hiddenTextinPasswort = passwotFeld.getAttribute("placeholder");
    Assert.assertEquals(hiddenTextinPasswort, "პაროლი");

  }

  @Test
  public void secondTest() throws InterruptedException {
    driver.get("https://www.swoop.ge/");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    WebElement shesvlaButton = driver.findElement(By.xpath("//div[@class = 'HeaderTools swoop-login']"));
    shesvlaButton.click();
    WebElement registraciaButton = driver.findElement(By.xpath("//*[@class=\"profile-tabs__item ui-state-default ui-corner-top\"]"));
    registraciaButton.click();
    sleep(2000);
    WebElement fizikuriPiri = driver.findElement(By.xpath("//*[@class=\"profile-tabs__link\"]"));
    Assert.assertTrue(fizikuriPiri.isEnabled());
    WebElement secondRegistracia = driver.findElement(By.xpath("//a[@onclick=\"checkPhysicalFormSubmit()\"]"));
    secondRegistracia.click();
    WebElement checkBoxIsChecked = driver.findElement(By.xpath("//input[@checked = \"checked\"]"));
    Assert.assertTrue(checkBoxIsChecked.isSelected());

    WebElement textSehiyvSaxeli = driver.findElement(By.xpath("//p[@id=\"physicalInfoMassage\"]"));
    Assert.assertTrue(textSehiyvSaxeli.isDisplayed());

  }

  @Test
  public void thirdTest() throws InterruptedException {
    driver.get("https://www.swoop.ge/");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
    WebElement dasvenebaButton = driver.findElement(By.xpath("//li[@class= \"MoreCategories\"]//*[@href=\"/category/24/dasveneba\"]"));
    dasvenebaButton.click();
    sleep(1000);
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollBy(0,700)");
    List<WebElement> danButtons = driver.findElements(By.xpath("//*[@placeholder=\"დან\"]"));
    for (WebElement visibleDanButton : danButtons) {
      if (visibleDanButton.isDisplayed()) {
        visibleDanButton.sendKeys("170");
        break;
      }
    }
    sleep(2000);
    List<WebElement> mdeButton = driver.findElements(By.xpath("//*[@placeholder=\"მდე\"]"));
    for (WebElement visibleMdeButton : mdeButton) {
      if (visibleMdeButton.isDisplayed()) {
        visibleMdeButton.sendKeys("180");
      }
    }
    sleep(2000);
    List<WebElement> dzebnaButton = driver.findElements(By.xpath("//div[@class=\"submit-button\"]"));
    for (WebElement visibleDzebnaButton : dzebnaButton) {
      if (visibleDzebnaButton.isDisplayed()) {
        visibleDzebnaButton.click();
      }
    }
    sleep(2000);


    List<WebElement> prices = driver.findElements(By.xpath("//p[contains(@class, \"deal-voucher-price\") and not (contains (@style, \"text-decoration: line-through;\"))]"));
    List<Integer> intPrices = new ArrayList<>();

    String numberRegex = "\\d+";

    for (WebElement price : prices) {
      String text = price.getText();
      Pattern pattern = Pattern.compile(numberRegex);
      Matcher matcher = pattern.matcher(text);

      while (matcher.find()) {
        String numberString = matcher.group();
        int number = Integer.parseInt(numberString);
        intPrices.add(number);
      }
    }
    for (int numbers : intPrices){
      Assert.assertTrue(numbers>=170 && numbers<=180 );
    }

  }

  @Test
  public void fourthTest() throws InterruptedException {
    driver.get("https://www.swoop.ge/");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    WebElement kinoButton = driver.findElement(By.xpath("//a[@href=\"/events\"]//*[@class=\"MenuIcon\"]"));
    kinoButton.click();
    WebElement firstMovie = driver.findElement(By.xpath("//div[@class=\"container cinema_container\"]//following::div"));
    Actions actions = new Actions(driver);
    actions.moveToElement(firstMovie).perform();
    WebElement yidvaElement = driver.findElement(By.xpath("//div[@class=\"info-cinema-ticket\"]"));
    yidvaElement.click();
    JavascriptExecutor js = (JavascriptExecutor) driver;
    //scroll vertical
    js.executeScript("window.scrollBy(0,200);");
    //scroll hotizontal
    js.executeScript("window.scrollBy(600,0);");
    WebElement saburtalo = driver.findElement(By.id("//a[@href=\"#607158\"]"));
    saburtalo.click();
    WebElement exceptedTime = driver.findElement(By.xpath("//p[@style=\"width:35px;\" and text() =\"19:30\"][1]"));
    WebElement exceptedMovie= driver.findElement(By.xpath("//p[@class=\"name\"]"));
    js.executeScript("window.scrollBy(0,900);");
    WebElement lastOption = driver.findElement(By.xpath("//section[@class=\"movie_content\"]//div[@id=\"607158\"]//child::div[4]"));
    //actions.moveToElement(lastOption).click().build().perform();
    lastOption.click();
    WebElement appearsTime = driver.findElement(By.xpath("//p[@class=\"movie-cinema\"][2]"));
    WebElement appearsMovie = driver.findElement(By.xpath("//p[@class=\"movie-title\"]"));
    WebElement appearsKinoTeatri = driver.findElement(By.xpath("//p[@class=\"movie-cinema\"][1]"));
    Assert.assertEquals(exceptedMovie.getText(),appearsMovie.getText());
    Assert.assertEquals(saburtalo.getText(),appearsKinoTeatri.getText());

    LocalDate currentDate = LocalDate.now();
    String[] georgianMonths = {
            "იანვარი", "თებერვალი", "მარტი", "აპრილი",
            "მაისი", "ივნისი", "ივლისი", "აგვისტო",
            "სექტემბერი", "ოქტომბერი", "ნოემბერი", "დეკემბერი"
    };
    Month currentMonth = currentDate.getMonth();
    int monthIndex = currentMonth.getValue() -1;
    String georginMontName = georgianMonths[monthIndex];
    // String datumAndMont = new currentDate + georginMontName;

    Assert.assertEquals(currentDate + georginMontName + exceptedTime.getText(), appearsTime.getText());

    // last element //div[@id="day-choose-24.07.2023" and @aria-labelledby="ui-id-20" and @data-sched="238919"]







  }




  @AfterMethod
  public void close(){
    driver.quit();
  }

}

