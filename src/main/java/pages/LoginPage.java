package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By emailField = By.id("email");
    private By passwordField = By.id("pass");
    private By signInButton = By.id("send2");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(signInButton).click();
    }
}
