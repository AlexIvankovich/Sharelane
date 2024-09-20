import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class AuthTest {


    @Test
    public void zipCodeShouldAccept5Digits() {

        WebDriver driver = new ChromeDriver();
        // Открыть страницу https://www.sharelane.com/cgi-bin/register.py
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        // Ввести 12345 в поле ZIP code
        WebElement zipCode = driver.findElement(By.name("zip_code"));
        zipCode.sendKeys("12345");
        // Нажать кнопку Continue
        WebElement continueButton = driver.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        // Ожидаемый результат: Отображается кнопка Register
        WebElement registerButton = driver.findElement(By.cssSelector("[value=Register]"));
        boolean displayed = registerButton.isDisplayed();
        assertTrue(displayed);
        // Закрыть браузер
        driver.quit();
    }

    @Test
    public void userMustBeRegisteredWhenEnteringWalidData() {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");

        WebElement firstName = driver.findElement(By.name("first_name"));
        firstName.sendKeys("Ivan");

        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("mail@gmail.com");

        WebElement password = driver.findElement(By.name("password1"));
        password.sendKeys("aM12345");

        WebElement confirmPassword = driver.findElement(By.name("password2"));
        confirmPassword.sendKeys("aM12345");

        WebElement registerButton = driver.findElement(By.cssSelector("[value=Register]"));
        registerButton.click();

        String actualConfirmMassage = driver.findElement(By.cssSelector("[class=confirmation_message]")).getText();
        String expectedConfirmMassage = "Account is created!";

        assertEquals(actualConfirmMassage, expectedConfirmMassage);

        driver.quit();


    }
}
