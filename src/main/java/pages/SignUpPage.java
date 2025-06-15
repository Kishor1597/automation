package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage extends BasePage {

    private By firstName = By.id("firstname");
    private By lastName = By.id("lastname");
    private By email = By.id("email_address");
    private By password = By.id("password");
    private By confirmPassword = By.id("password-confirmation");
    private By createButton = By.cssSelector("button[title='Create an Account']");

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public void fillForm(String fname, String lname, String mail, String pwd) {
        driver.findElement(firstName).sendKeys(fname);
        driver.findElement(lastName).sendKeys(lname);
        driver.findElement(email).sendKeys(mail);
        driver.findElement(password).sendKeys(pwd);
        driver.findElement(confirmPassword).sendKeys(pwd);
    }

    public void submitForm() {
        driver.findElement(createButton).click();
    }
}
